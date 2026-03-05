package interview.presence_2026;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    // 监听器接口
    public interface EventListener<T> {
        void onEvent(T event);
    }

    // ===================== 核心存储 =====================
    // 事件类型 -> 所有监听器
    private final Map<Class<?>, List<EventListener<?>>> listeners = new ConcurrentHashMap<>();

    // ===================== 注册 =====================
    // 普通注册：一直监听
    public <T> void register(Class<T> eventType, EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(listener);
    }

    // ===================== 解注册 =====================
    public synchronized <T> void unregister(Class<T> eventType, EventListener<T> listener) {
        List<EventListener<?>> list = listeners.get(eventType);
        if (list != null) {
            list.remove(listener);
        }
    }

    // 单次注册：执行一次自动解绑
    public <T> void registerOnce(Class<T> eventType, EventListener<T> listener) {
        // 用数组包装，让 lambda 能引用自身
        EventListener<?>[] wrapper = new EventListener[1];

        wrapper[0] = (EventListener<T>) event -> {
            // 先执行业务
            listener.onEvent(event);
            // 再删除【自己】
            unregister(eventType, (EventListener<T>) wrapper[0]);
        };

        register(eventType, (EventListener<T>) wrapper[0]);

        // error solution
//        EventListener<T> onceListener = event -> {
//            listener.onEvent(event);
//            unregister(eventType, listener); // 这里删的是原始 listener！
//        };
//        register(eventType, onceListener); // 但存进去的是包装类 onceListener
    }

    // ===================== 发布事件 =====================
    public <T> void post(T event) {
        Class<?> eventType = event.getClass();
        List<EventListener<?>> list = listeners.get(eventType);
        if (list == null || list.isEmpty()) return;

        // 安全遍历触发
        for (EventListener<?> listener : list) {
            try {
                ((EventListener<T>) listener).onEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EventBus bus = new EventBus();

        // 普通监听
        bus.register(String.class, msg -> System.out.println("普通监听：" + msg));

        // 单次监听
        bus.registerOnce(Integer.class, num -> System.out.println("单次监听：" + num));

        bus.post("hello");    // 触发
        bus.post("hi");       // 触发
        bus.post(100);        // 触发一次
        bus.post(200);        // 不会触发
    }

}
