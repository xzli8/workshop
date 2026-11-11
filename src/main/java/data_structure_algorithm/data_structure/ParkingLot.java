package data_structure_algorithm.data_structure;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 停车场（OOD，重点是思路，代码是简化骨架）
 *
 * ref: LeetCode 1603 Design Parking System 是极简版（只有三种车位计数）
 *
 * 1. 先澄清需求（面试时先问，再动手）
 * - 几层？车位有哪几种尺寸？车有哪几种？
 * - 车能不能停更大的车位（小车停大车位）？
 * - 怎么收费：按小时？按车型？
 * - 是否有多个入口同时进车（并发）？
 * - 本题假设：多层；车位 SMALL / MEDIUM / LARGE；摩托车 / 轿车 / 卡车；小车可以停大车位；按小时 × 车型单价收费
 *
 * 2. 核心类（名词 → 类）
 * - ParkingLot：入口，对外提供 park / leave / 空位查询
 * - Level：一层，按尺寸维护空闲车位
 * - Spot：一个车位，有尺寸和编号
 * - Vehicle：车牌 + 车型；车型决定最小车位尺寸
 * - Ticket：停车凭证（ticketId、车、车位、入场时间）
 * - SpotAllocationStrategy：选哪个车位（策略模式）
 * - PricingStrategy：怎么计费（策略模式）
 *
 * 3. 关键设计
 * - 找空位 O(1)：每层每种尺寸一个空闲车位队列，停车 poll、离开 offer；不要每次遍历所有车位
 * - 车能停的尺寸：从车型的最小尺寸往大找（小车先找小车位，没有再找大的，避免大车位被占光）
 * - 分配策略可替换：默认"最低一层、最小合适尺寸"；可换成"离入口最近""均匀分布到各层"
 * - 计费可替换：按小时、按车型、白天夜间不同价、会员折扣，都实现 PricingStrategy
 * - 离场凭 ticketId 查 Ticket，归还车位，计算费用
 *
 * 4. 并发（多个入口同时进车）
 * - 两辆车不能分到同一个车位：分配车位的操作必须原子
 * - 简单做法：每层一把锁，在锁内 poll 空闲队列；不同层可以并行
 * - 或者每种尺寸用并发队列（ConcurrentLinkedDeque），poll 本身是原子的
 *
 * 5. 常见扩展
 * - 新增充电桩车位：新增车位类型 + 分配规则，不改原有代码（开闭原则）
 * - 入口显示屏实时显示空位：空位变化时通知显示屏（观察者模式）
 * - 预约车位：车位增加 RESERVED 状态，预约超时自动释放
 */
public class ParkingLot {

    public enum SpotSize {SMALL, MEDIUM, LARGE}

    public enum VehicleType {
        MOTORCYCLE(SpotSize.SMALL), CAR(SpotSize.MEDIUM), TRUCK(SpotSize.LARGE);

        final SpotSize minSize;

        VehicleType(SpotSize minSize) {
            this.minSize = minSize;
        }
    }

    public static class Vehicle {
        final String plate;
        final VehicleType type;

        public Vehicle(String plate, VehicleType type) {
            this.plate = plate;
            this.type = type;
        }
    }

    public static class Spot {
        final int level;
        final int id;
        final SpotSize size;

        Spot(int level, int id, SpotSize size) {
            this.level = level;
            this.id = id;
            this.size = size;
        }
    }

    public static class Ticket {
        final String id;
        final Vehicle vehicle;
        final Spot spot;
        final long entryMillis;

        Ticket(Vehicle vehicle, Spot spot, long entryMillis) {
            this.id = UUID.randomUUID().toString();
            this.vehicle = vehicle;
            this.spot = spot;
            this.entryMillis = entryMillis;
        }
    }

    /**
     * 一层：每种尺寸一个空闲队列，一把锁保证分配原子
     */
    public static class Level {
        final int no;
        private final Map<SpotSize, Deque<Spot>> free = new EnumMap<>(SpotSize.class);

        public Level(int no, int small, int medium, int large) {
            this.no = no;
            int id = 0;
            for (SpotSize s : SpotSize.values()) free.put(s, new ArrayDeque<>());
            for (int i = 0; i < small; i++) free.get(SpotSize.SMALL).offer(new Spot(no, id++, SpotSize.SMALL));
            for (int i = 0; i < medium; i++) free.get(SpotSize.MEDIUM).offer(new Spot(no, id++, SpotSize.MEDIUM));
            for (int i = 0; i < large; i++) free.get(SpotSize.LARGE).offer(new Spot(no, id++, SpotSize.LARGE));
        }

        // 从车型的最小尺寸往大找
        synchronized Optional<Spot> allocate(VehicleType type) {
            for (SpotSize s : SpotSize.values()) {
                if (s.compareTo(type.minSize) >= 0 && !free.get(s).isEmpty()) {
                    return Optional.of(free.get(s).poll());
                }
            }
            return Optional.empty();
        }

        synchronized void release(Spot spot) {
            free.get(spot.size).offer(spot);
        }

        synchronized int freeCount(SpotSize size) {
            return free.get(size).size();
        }
    }

    public interface SpotAllocationStrategy {
        Optional<Spot> allocate(List<Level> levels, VehicleType type);
    }

    // 默认：从最低一层开始找
    public static class LowestLevelFirst implements SpotAllocationStrategy {
        @Override
        public Optional<Spot> allocate(List<Level> levels, VehicleType type) {
            for (Level level : levels) {
                Optional<Spot> spot = level.allocate(type);
                if (spot.isPresent()) return spot;
            }
            return Optional.empty();
        }
    }

    public interface PricingStrategy {
        long fee(Ticket ticket, long exitMillis);
    }

    // 按小时 × 车型单价，不足一小时按一小时
    public static class HourlyPricing implements PricingStrategy {
        private final Map<VehicleType, Long> hourlyRate;

        public HourlyPricing(Map<VehicleType, Long> hourlyRate) {
            this.hourlyRate = hourlyRate;
        }

        @Override
        public long fee(Ticket ticket, long exitMillis) {
            long hours = Math.max(1, (exitMillis - ticket.entryMillis + 3_600_000 - 1) / 3_600_000);
            return hours * hourlyRate.get(ticket.vehicle.type);
        }
    }

    private final List<Level> levels;
    private final SpotAllocationStrategy allocation;
    private final PricingStrategy pricing;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();

    public ParkingLot(List<Level> levels, SpotAllocationStrategy allocation, PricingStrategy pricing) {
        this.levels = levels;
        this.allocation = allocation;
        this.pricing = pricing;
    }

    // 停车：没有空位返回 empty
    public Optional<Ticket> park(Vehicle vehicle, long nowMillis) {
        return allocation.allocate(levels, vehicle.type).map(spot -> {
            Ticket t = new Ticket(vehicle, spot, nowMillis);
            activeTickets.put(t.id, t);
            return t;
        });
    }

    // 离场：归还车位，返回费用
    public long leave(String ticketId, long nowMillis) {
        Ticket t = activeTickets.remove(ticketId);
        if (t == null) throw new IllegalArgumentException("invalid ticket");
        levels.get(t.spot.level).release(t.spot);
        return pricing.fee(t, nowMillis);
    }

    public int freeCount(SpotSize size) {
        int total = 0;
        for (Level level : levels) total += level.freeCount(size);
        return total;
    }

    public static class TestParkingLot {

        private ParkingLot newLot() {
            List<Level> levels = new ArrayList<>();
            levels.add(new Level(0, 1, 1, 0));
            levels.add(new Level(1, 0, 0, 1));
            Map<VehicleType, Long> rate = new EnumMap<>(VehicleType.class);
            rate.put(VehicleType.MOTORCYCLE, 5L);
            rate.put(VehicleType.CAR, 10L);
            rate.put(VehicleType.TRUCK, 20L);
            return new ParkingLot(levels, new LowestLevelFirst(), new HourlyPricing(rate));
        }

        @Test
        public void parkAndLeave() {
            ParkingLot lot = newLot();
            Ticket t = lot.park(new Vehicle("A1", VehicleType.CAR), 0).get();
            Assert.assertEquals(SpotSize.MEDIUM, t.spot.size);
            Assert.assertEquals(0, lot.freeCount(SpotSize.MEDIUM));
            // 停了 90 分钟，按 2 小时计费
            Assert.assertEquals(20, lot.leave(t.id, 90 * 60_000));
            Assert.assertEquals(1, lot.freeCount(SpotSize.MEDIUM));
        }

        @Test
        public void smallVehicleFallsBackToLargerSpot() {
            ParkingLot lot = newLot();
            lot.park(new Vehicle("M1", VehicleType.MOTORCYCLE), 0);
            // 小车位满了，摩托车停到中车位
            Ticket t = lot.park(new Vehicle("M2", VehicleType.MOTORCYCLE), 0).get();
            Assert.assertEquals(SpotSize.MEDIUM, t.spot.size);
        }

        @Test
        public void noSpotReturnsEmpty() {
            ParkingLot lot = newLot();
            Assert.assertTrue(lot.park(new Vehicle("T1", VehicleType.TRUCK), 0).isPresent());
            Assert.assertFalse(lot.park(new Vehicle("T2", VehicleType.TRUCK), 0).isPresent());
        }
    }
}
