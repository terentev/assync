package com.assync;

public interface Assync<V> {
    boolean isComplete();

    void register(Runnable run);

    V result();

    Throwable error();
}
