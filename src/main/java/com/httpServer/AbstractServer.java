package com.httpServer;

import com.struct.Contexts;

public abstract class AbstractServer implements IServer {
    protected AbstractServer() {
        initContext();
    }

    private Contexts contexts = new Contexts();

    protected final void initContext() {

    }

    public Contexts getContexts() {
        return contexts;
    }
}
