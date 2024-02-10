package concurrent.singleton.impl;

import concurrent.singleton.Singleton;

public enum Enum implements Singleton {

    INSTANCE,
    ;

    @Override
    public Singleton getInstance() {
        return INSTANCE;
    }

}
