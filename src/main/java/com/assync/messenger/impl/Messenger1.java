package com.assync.messenger.impl;


import com.assync.Messenger;

import java.util.Collection;
import java.util.LinkedList;

public class Messenger1 implements Messenger {

    private boolean complete = false;
    private final Collection<Runnable> buffer = new LinkedList<Runnable>();

    public void done() {
        synchronized (buffer) {
            complete = true;
        }
        for (Runnable r : buffer)
            r.run();
        buffer.clear();
    }

    public void register(Runnable callback) {
        boolean complete = false;
        synchronized (buffer) {
            if (this.complete) {
                complete = true;
            } else {
                buffer.add(callback);
            }
        }
        if (complete) {
            callback.run();
        }
    }
}


