package com.assync.assyncs;


import com.assync.Assync;
import com.assync.CompletionHandler;
import com.assync.Wrapper;
import com.assync.assync.impl.AssyncImpl;
import com.assync.generic.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Assyncs {

    public static <V, A, T> Assync<V> invoke(T obj, Wrapper<V, T> wrapper, Object... args) {
        return new AssyncImpl<V, A, T>(obj, wrapper, args);
    }

    public static <V, A, T> Assync<V> run(T obj, Wrapper<V, T> wrapper, Object[] args) {
        return new AssyncImpl<V, A, T>(obj, wrapper, args);
    }

    public static void done(Assync<?>... values) throws ExecutionException, InterruptedException {
        future(run(null, null, values)).get();
    }

    @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
    public static <V, A> void handler(final Assync<V> assync, final ExecutorService es, final A attachment, final CompletionHandler<V, ? super A> handler) {
        assync.register(new Runnable() {
            @Override
            public void run() {
                Runnable run = new Runnable() {
                    AtomicBoolean bool = new AtomicBoolean();

                    @Override
                    public void run() {
                        if (bool.getAndSet(true)) return;
                        if (assync.error() == null) {
                            handler.completed(assync.result(), attachment);
                        } else {
                            handler.failed(assync.error(), attachment);
                        }
                    }
                };
                try {
                    es.submit(run);
                } catch (Throwable t0) {
                    try {
                        Thread thread = new Thread(run);
                        thread.start();
                    } catch (Throwable t1) {
                        run.run();
                    }
                }
            }
        });
    }

    public static <V> Future<V> future(final Assync<V> assync) {
        final FutureTask<V> future = new FutureTask<V>(new Callable<V>() {
            @SuppressWarnings({"ThrowableResultOfMethodCallIgnored"})
            @Override
            public V call() throws Exception {
                if (assync.error() == null) {
                    return assync.result();
                } else {
                    throw new ExecutionException(assync.error());
                }
            }
        });
        assync.register(new Runnable() {
            @Override
            public void run() {
                future.run();
            }
        });
        return future;
    }

    public static <V, A, T> Assync<V> call(T obj, Wrapper0A<V, T> wrapper) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{});
    }


    public static <V, A, T, P0> Assync<V> call(T obj, Wrapper1A<V, T, P0> wrapper, P0 p0) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0});
    }

    public static <V, A, T, P0> Assync<V> call(T obj, Wrapper1A<V, T, P0> wrapper, Assync<? extends P0> p0) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0});
    }


    public static <V, A, T, P0, P1> Assync<V> call(T obj, Wrapper2A<V, T, P0, P1> wrapper, P0 p0, P1 p1) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1});
    }

    public static <V, A, T, P0, P1> Assync<V> call(T obj, Wrapper2A<V, T, P0, P1> wrapper, Assync<? extends P0> p0, P1 p1) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1});
    }

    public static <V, A, T, P0, P1> Assync<V> call(T obj, Wrapper2A<V, T, P0, P1> wrapper, P0 p0, Assync<? extends P1> p1) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1});
    }

    public static <V, A, T, P0, P1> Assync<V> call(T obj, Wrapper2A<V, T, P0, P1> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1});
    }


    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, P0 p0, P1 p1, P2 p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }

    public static <V, A, T, P0, P1, P2> Assync<V> call(T obj, Wrapper3A<V, T, P0, P1, P2> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2});
    }


    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, P1 p1, P2 p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2, P3 p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, P1 p1, P2 p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }

    public static <V, A, T, P0, P1, P2, P3> Assync<V> call(T obj, Wrapper4A<V, T, P0, P1, P2, P3> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2, Assync<? extends P3> p3) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3});
    }


    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, P2 p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2, P3 p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, P2 p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2, Assync<? extends P3> p3, P4 p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, P2 p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2, P3 p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, P2 p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, P2 p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, P2 p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, P2 p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, P1 p1, Assync<? extends P2> p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, P1 p1, Assync<? extends P2> p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, P0 p0, Assync<? extends P1> p1, Assync<? extends P2> p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }

    public static <V, A, T, P0, P1, P2, P3, P4> Assync<V> call(T obj, Wrapper5A<V, T, P0, P1, P2, P3, P4> wrapper, Assync<? extends P0> p0, Assync<? extends P1> p1, Assync<? extends P2> p2, Assync<? extends P3> p3, Assync<? extends P4> p4) {
        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{p0, p1, p2, p3, p4});
    }
}