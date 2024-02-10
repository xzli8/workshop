package concurrent.singleton;

public interface Singleton {

    /**
     * 需要重点考虑的几个问题：
     *      1.构造函数需要是 private 访问权限的，这样才能避免外部通过 new 创建实例；
     *      2.考虑对象创建时的线程安全问题；
     *      3.考虑是否支持延迟加载；
     *      4.考虑 getInstance() 性能是否高（是否加锁）
     */

    Singleton getInstance();

}
