package com.assync.messenger.impl;



import com.assync.Messenger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Messenger2 implements Messenger {

    private volatile boolean complete = false;
    private final Collection<Runnable> buffer = new LinkedList<Runnable>();
    final Lock lock = new ReentrantLock();

    public void done() {
        Collection<Runnable> b;
        lock.lock();
        try {
            complete = true;
            b = buffer;
        } finally {
            lock.unlock();
        }
        for (Runnable item : b) {
            item.run();
        }
        buffer.clear();
    }

    public void register(Runnable callback) {
        boolean complete = false;
        lock.lock();
        try {
            if (this.complete) {
                complete = true;
            } else {
                buffer.add(callback);
            }
        } finally {
            lock.unlock();
        }
        if (complete)
            callback.run();
    }

}