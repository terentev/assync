package com.assync;

import com.assync.assyncs.Assyncs;
import com.assync.utils.Callablex;
import com.assync.utils.Wrappers;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tests0 {

    static ExecutorService es = Executors.newCachedThreadPool();

    static class Abc {
        int aaa(int a, int b) {
            return a + b;
        }
    }

    static class AbcWrap {

        private static Wrapper<Integer, Abc> a = new Wrapper<Integer, Abc>() {
            @Override
            public <A> void call(final Abc obj, final Object[] args, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Throwable t = null;
                        Integer a = null;
                        try {
                            a = obj.aaa((Integer) args[0], (Integer) args[1]);
                        } catch (Throwable exc) {
                            t = exc;
                        }

                        if (t == null) {
                            handler.completed(a, attachment);
                        } else {
                            handler.failed(t, attachment);
                        }
                        return null;
                    }
                });
            }
        };

        private static Wrapper<Integer, Abc> a1 = Wrappers.get(es, new Callablex<Integer, Abc>() {
            @Override
            public Integer call(Abc o, Object[] args) throws Throwable {
                return o.aaa((Integer) args[0], (Integer) args[1]);
            }
        });

        @SuppressWarnings("unchecked")
        public static Wrapper<Integer, Abc> aaa() {
            return a;
        }

        @SuppressWarnings("unchecked")
        static Wrapper<Integer, Abc> aaa1() {
            return a1;
        }

        static Wrapper<Integer, Abc> aaa2() throws NoSuchMethodException {
            return Wrappers.get(es, Abc.class.getMethod("aaa", Integer.class, Integer.class));
        }

        static Wrapper<Integer, Abc> aaa3() throws NoSuchMethodException {
            return Wrappers.get(es, Abc.class, "aaa");
        }

    }

    @Test
    public void test0() throws Throwable {
        Abc abc = new Abc();
        int v0 = abc.aaa(1, 2);
        Assync<Integer> a0 = Assyncs.invoke(abc, AbcWrap.aaa(), 1, 2);
        Assyncs.future(a0).get();
        Assert.assertEquals((Integer) v0, a0.result());
    }

    @Test
    public void test1() throws Throwable {
        Abc abc = new Abc();
        int v0 = abc.aaa(1, 2);
        int v1 = abc.aaa(1, v0);
        int v2 = abc.aaa(v0, v1);
        int v3 = abc.aaa(v2, v2);
        Assync<Integer> a0 = Assyncs.invoke(abc, AbcWrap.aaa(), 1, 2);
        Assync<Integer> a1 = Assyncs.invoke(abc, AbcWrap.aaa(), 1, a0);
        Assync<Integer> a2 = Assyncs.invoke(abc, AbcWrap.aaa(), a0, a1);
        Assync<Integer> a3 = Assyncs.invoke(abc, AbcWrap.aaa(), a2, a2);
        Assyncs.future(a3).get();
        Assert.assertEquals((Integer) v3, a3.result());
    }

    @Test
    public void test2() throws Throwable {
        Abc abc = new Abc();
        int v0 = abc.aaa(1, 2);
        int v1 = abc.aaa(1, v0);
        int v2 = abc.aaa(v0, v1);
        final int v3 = abc.aaa(v2, v2);
        Assync<Integer> a0 = Assyncs.invoke(abc, AbcWrap.aaa(), 1, 2);
        Assync<Integer> a1 = Assyncs.invoke(abc, AbcWrap.aaa(), 1, a0);
        Assync<Integer> a2 = Assyncs.invoke(abc, AbcWrap.aaa(), a0, a1);
        final Assync<Integer> a3 = Assyncs.invoke(abc, AbcWrap.aaa(), a2, a2);
    }
}
