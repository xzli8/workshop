package concurrent.singleton.impl;

import concurrent.singleton.Singleton;

public class Lazy implements Singleton {

    // private constructor
    private Lazy() {}

    private static Lazy instance = null;

    @Override
    public synchronized Singleton getInstance() {
        if (instance == null) instance = new Lazy();
        return instance;
    }

}
