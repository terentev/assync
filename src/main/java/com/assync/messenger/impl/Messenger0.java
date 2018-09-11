package com.assync.messenger.impl;



import com.assync.Messenger;

import java.util.Collection;
import java.util.LinkedList;


public class Messenger0 implements Messenger {

    private boolean complete = false;
    private final Collection<Runnable> buffer = new LinkedList<Runnable>();

    public void done() {
        synchronized (buffer) {
            complete = true;
            for (Runnable o : buffer) {
                o.run();
            }
            buffer.clear();
        }
    }

    public void register(Runnable callback) {
        synchronized (buffer) {
            if (complete) {
                callback.run();
            } else {
                buffer.add(callback);
            }
        }
    }
}


