package java.concurrent.singleton.impl;

import java.concurrent.singleton.Singleton;

public enum Enum implements Singleton {

    INSTANCE,
    ;

    @Override
    public Singleton getInstance() {
        return INSTANCE;
    }

}
