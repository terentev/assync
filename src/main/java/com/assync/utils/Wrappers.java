package com.assync.utils;


import com.assync.CompletionHandler;
import com.assync.Wrapper;
import com.assync.generic.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;


public class Wrappers {

    public static <V, T> Wrapper<V, T> get(final ExecutorService es, final Method method) {
        return new Wrapper<V, T>() {
            public <A> void call(final T obj, final Object[] args, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    @SuppressWarnings("unchecked")
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = (V) method.invoke(obj, args);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T> Wrapper<V, T> get(final ExecutorService es, Class<T> clazz, String methodName) throws NoSuchMethodException {
        return get(es, Helper.get(clazz, methodName));
    }

    public static <V, T> Wrapper<V, T> get(final ExecutorService es, final Callablex<V, T> call) {
        return new Wrapper<V, T>() {
            public <A> void call(final T obj, final Object[] args, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj, args);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T> Wrapper0A<V, T> get(final ExecutorService es, final Callablex0<V, T> call) {
        return new Wrapper0A<V, T>() {
            @Override
            public <A> void call(final T obj, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T, P0> Wrapper1A<V, T, P0> get(final ExecutorService es, final Callablex1<V, T, P0> call) {
        return new Wrapper1A<V, T, P0>() {
            @Override
            public <A> void call(final T obj, final P0 p0, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj, p0);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T, P0, P1> Wrapper2A<V, T, P0, P1> get(final ExecutorService es, final Callablex2<V, T, P0, P1> call) {
        return new Wrapper2A<V, T, P0, P1>() {
            @Override
            public <A> void call(final T obj, final P0 p0, final P1 p1, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj, p0, p1);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T, P0, P1, P2> Wrapper3A<V, T, P0, P1, P2> get(final ExecutorService es, final Callablex3<V, T, P0, P1, P2> call) {
        return new Wrapper3A<V, T, P0, P1, P2>() {
            @Override
            public <A> void call(final T obj, final P0 p0, final P1 p1, final P2 p2, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj, p0, p1, p2);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T, P0, P1, P2, P3> Wrapper4A<V, T, P0, P1, P2, P3> get(final ExecutorService es, final Callablex4<V, T, P0, P1, P2, P3> call) {
        return new Wrapper4A<V, T, P0, P1, P2, P3>() {
            @Override
            public <A> void call(final T obj, final P0 p0, final P1 p1, final P2 p2, final P3 p3, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj, p0, p1, p2, p3);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    public static <V, T, P0, P1, P2, P3, P4> Wrapper5A<V, T, P0, P1, P2, P3, P4> get(final ExecutorService es, final Callablex5<V, T, P0, P1, P2, P3, P4> call) {
        return new Wrapper5A<V, T, P0, P1, P2, P3, P4>() {
            @Override
            public <A> void call(final T obj, final P0 p0, final P1 p1, final P2 p2, final P3 p3, final P4 p4, final A attachment, final CompletionHandler<V, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    public Void call() throws Exception {
                        V result = null;
                        Throwable th = null;
                        try {
                            result = call.call(obj, p0, p1, p2, p3, p4);
                        } catch (Throwable exc) {
                            th = exc;
                        }

                        if (th == null) {
                            handler.completed(result, attachment);
                        } else {
                            handler.failed(th, attachment);
                        }
                        return null;
                    }
                });
            }
        };
    }

    private static class Helper {
        public static Method get(Class<?> clazz, String methodName) throws NoSuchMethodException {
            int count = 0;
            Method r = null;
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(methodName)) {
                    r = method;
                    count++;
                }
            }
            if (count != 1)
                throw new NoSuchMethodException(
                        String.format("Class %s have too many method with name %s", clazz.getName(), methodName));
            return r;
        }
    }

}
