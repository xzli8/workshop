package concurrent.singleton.impl;

import concurrent.singleton.Singleton;

public class StaticInnerClass implements Singleton {

    // private constructor
    private StaticInnerClass() {}

    private static class Holder {
        private static final StaticInnerClass instance = new StaticInnerClass();
    }

    @Override
    public Singleton getInstance() {
        return Holder.instance;
    }

}
