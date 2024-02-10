package system_design;

public class SnowflakeDistributeId {

    // 上一次生成ID的时间戳
    private long lastTimestamp = -1L;

    // 数据中心(机房) id
    private long datacenterId;

    // 机器ID
    private long workerId;

    // 毫秒内序列
    private long sequence = 0L;

    // 开始时间戳
    private final long twepoch = 1420041600000L;

    // 机房号，的ID所占的位数 5个bit 最大:11111(2进制)--> 31(10进制)
    private final long datacenterIdBits = 5L;

    // 机器ID所占的位数 5个bit 最大:11111(2进制)--> 31(10进制)
    private long workerIdBits = 5L;

    // 5 bit最多只能有31个数字，机房id最多只能是32以内
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    // 5 bit最多只能有31个数字，就是说机器id最多只能是32以内
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    // 同一时间的序列所占的位数 12个bit 111111111111 = 4095  最多就是同一毫秒生成4096个
    private long sequenceBits = 12L;

    // workerId的偏移量
    private long workerIdShift = sequenceBits;

    // datacenterId的偏移量
    private long datacenterIdShift = sequenceBits + workerIdBits;

    // timestampLeft的偏移量
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    // 序列号掩码 4095 (0b111111111111=0xfff=4095)
    // 用于序号的与运算，保证序号最大值在0-4095之间
    private long sequenceMask = -1L ^ (-1L << sequenceBits);




    // 构造器
    public SnowflakeDistributeId(long workerId, long datacenterId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    // 获取下一个随机的ID
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        // 如果当前时间小于上一次生成ID的时间戳，说明系统时钟回退，此时需要抛异常
        if (timestamp < lastTimestamp) throw new RuntimeException("Clock moved backward");

        // 如果是同一毫秒内生成的，则进行毫秒内序列
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;

            // sequence序列大于4095
            if (sequence == 0) {
                // 调用到下一个时间戳的方法
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 记录上一次生成ID的时间戳
        lastTimestamp = timestamp;

        // 偏移计算
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        // 获取最新时间戳
        long timestamp = System.currentTimeMillis();
        // 如果发现最新的时间戳小于或者等于序列号已经超4095的那个时间戳
        while (timestamp <= lastTimestamp) {
            // 不符合则继续
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }



    public static void main(String[] args) {
        SnowflakeDistributeId idWorker = new SnowflakeDistributeId(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }


}
