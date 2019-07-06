package com.struct;

public abstract class AbstractManager {
    private Contexts contexts;

    protected AbstractManager(Contexts newContexts) {
        this.contexts = newContexts;
    }

    public Contexts getContexts() {
        return contexts;
    }
}
