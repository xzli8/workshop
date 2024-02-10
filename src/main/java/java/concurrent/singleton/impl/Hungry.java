package java.concurrent.singleton.impl;

import java.concurrent.singleton.Singleton;

public class Hungry implements Singleton {

    // private constructor
    private Hungry() {}

    private static final Hungry instance = new Hungry();

    @Override
    public Singleton getInstance() {
        return instance;
    }

}
