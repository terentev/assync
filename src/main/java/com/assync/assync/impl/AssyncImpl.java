package com.assync.assync.impl;


import com.assync.Assync;
import com.assync.CompletionHandler;
import com.assync.Messenger;
import com.assync.Wrapper;
import com.assync.messenger.impl.Messenger1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class AssyncImpl<V, A, T> implements Assync<V> {

    private Wrapper<V, T> wrapper;
    private T obj;
    private Object[] args;

    private Messenger messenger = new Messenger1();
    private AtomicInteger need = new AtomicInteger();
    private AtomicBoolean runError = new AtomicBoolean();
    private AtomicBoolean run = new AtomicBoolean();

    protected V result;
    protected Throwable error;

    private volatile boolean complete;

    public AssyncImpl(T obj, Wrapper<V, T> wrapper, Object[] args) {
        this.wrapper = wrapper;
        this.obj = obj;
        this.args = args;

        if (args != null) {
            int count = 0;
            for (Object arg : args) {
                if (!(arg instanceof Assync)) continue;
                final Assync a = (Assync) arg;
                a.register(new Runnable() {
                    @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
                    public void run() {
                        if (a.error() != null) {
                            runError(a.error());
                        } else {
                            runOk(-1);
                        }
                    }
                });
                count++;
            }
            runOk(count);
        } else {
            runOk(0);
        }
    }

    public void register(Runnable run) {
        messenger.register(run);
    }

    private void runError(Throwable error) {
        if (!runError.getAndSet(true)) {
            start();
            complete(null, error);
        }
    }

    private void runOk(int delta) {
        if (need.addAndGet(delta) == 0) {
            start();
            if (wrapper == null) {
                complete(null, null);
                return;
            }
            Object[] args = Helper.args(this.args);
            try {
                wrapper.call(this.obj, args, null,
                        new CompletionHandler<V, Void>() {
                            @Override
                            public void completed(V result, Void attachment) {
                                complete(result, null);
                            }

                            @Override
                            public void failed(Throwable error, Void attachment) {
                                complete(null, error);
                            }
                        }
                );
            } catch (Throwable error) {
                complete(null, error);
            }
        }
    }

    private void complete(V result, Throwable error) {
        if (run.getAndSet(true)) return;
        this.result = result;
        this.error = error;
        complete = true;
        end();
        messenger.done();
    }

    public V result() {
        if (complete)
            return result;
        else
            throw new IllegalStateException("Cannot get the value until the task is complete");
    }

    public Throwable error() {
        if (complete)
            return error;
        else
            throw new IllegalStateException("Cannot get the value until the task is complete");
    }

    public boolean isComplete() {
        return complete;
    }

    protected void start() {
        //System.out.println("start");
    }

    protected void end() {
        //System.out.println("end");
    }

    private static class Helper {

        public static Object[] args(Object[] args) {
            if (args == null)
                return null;
            Object[] r = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Assync) {
                    r[i] = ((Assync) args[i]).result();
                } else {
                    r[i] = args[i];
                }
            }
            return r;
        }
    }


}
