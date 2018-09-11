package com.assync;

public interface Messenger {
    public void done();

    public void register(Runnable callback);
}
