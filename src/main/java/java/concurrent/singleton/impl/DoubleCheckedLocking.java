package java.concurrent.singleton.impl;

import java.concurrent.singleton.Singleton;

public class DoubleCheckedLocking implements Singleton {

    // private constructor
    private DoubleCheckedLocking() {}

    private static volatile DoubleCheckedLocking instance = null;

    @Override
    public Singleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) instance = new DoubleCheckedLocking();
            }
        }
        return instance;
    }

}
