package com.assync;

import com.assync.assyncs.Assyncs;
import com.assync.generic.Callablex2;
import com.assync.generic.Wrapper2A;
import com.assync.utils.Callablex;
import com.assync.utils.Wrappers;

import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    static ExecutorService es = Executors.newCachedThreadPool();

    static class Abc {
        public int a(int a, int b) throws InterruptedException {
            Thread.sleep(1);
            return a + b;
        }
    }

    static class AbcWrapper {

        private static Wrapper<Integer, Abc> a = new Wrapper<Integer, Abc>() {
            @Override
            public <A> void call(final Abc obj, final Object[] args, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            int a = obj.a((Integer) args[0], (Integer) args[1]);
                            handler.completed(a, attachment);
                        } catch (Throwable t) {
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
                return o.a((Integer) args[0], (Integer) args[1]);
            }
        });


        public static Wrapper2A<Integer, Abc, Integer, Integer> a2 = new Wrapper2A<Integer, Abc, Integer, Integer>() {
            @Override
            public <A> void call(final Abc obj, final Integer p0, final Integer p1, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            int a = obj.a(p0, p1);
                            handler.completed(a, attachment);
                        } catch (Throwable t) {
                            handler.failed(t, attachment);
                        }
                        return null;
                    }
                });
            }
        };

        public static Wrapper2A<Integer, Abc, Integer, Integer> a3 = Wrappers.get(es, new Callablex2<Integer, Abc, Integer, Integer>() {
            @Override
            public Integer call(Abc obj, Integer p0, Integer p1) throws Throwable {
                return obj.a(p0, p1);
            }
        });

        @SuppressWarnings("unchecked")
        static Wrapper<Integer, Abc> a() {
            return a;
        }

        @SuppressWarnings("unchecked")
        static Wrapper<Integer, Abc> a1() {
            return a1;
        }

        static Wrapper<Integer, Abc> a2() throws NoSuchMethodException {
            return Wrappers.get(es, Abc.class.getMethod("a", Integer.class, Integer.class));
        }

        static Wrapper<Integer, Abc> a3() throws NoSuchMethodException {
            return Wrappers.get(es, Abc.class, "a");
        }
    }

    public static String generic(int n) {
        String r = "";

        String s = "" +
                "    public static <V, A, T $p> Assync<V> call(T obj, Wrapper$nA<V, T $p> wrapper $h $j) {   \n" +
                "        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{$q}, $a, $c);             \n" +
                "    }                                                                                       \n" +
                "                                                                                            \n";
        if (n == 0) {
            r += s.replace("$n", n + "").replace("$p", "").replace("$q", "").replace("$h", "")
                    .replace("$a", "null").replace("$c", "null").replace("$j", "");
            r += s.replace("$n", n + "").replace("$p", "").replace("$q", "").replace("$h", "")
                    .replace("$a", "attachment").replace("$c", "handler").replace("$j", ", A attachment, CompletionHandler<V, ? super A> handler");
            return r;
        }

        String p = "";
        for (int i = 0; i < n; i++) {
            p += "," + "P" + i;
        }

        String q = "";
        for (int i = 0; i < n; i++) {
            q += "p" + i + ",";
        }
        q = q.substring(0, q.length() - 1);

        for (int i = 0; i < 2 << n - 1; i++) {
            String h = "";
            for (int j = 0; j < n; j++) {
                if ((i & 1 << j) == 0) {
                    h += String.format(",P%d p%d", j, j);
                } else {
                    h += String.format(",Assync<? extends P%d> p%d", j, j);
                }
            }
            r += s.replace("$n", n + "").replace("$p", p).replace("$q", q).replace("$h", h)
                    .replace("$a", "null").replace("$c", "null").replace("$j", "");
            r += s.replace("$n", n + "").replace("$p", p).replace("$q", q).replace("$h", h)
                    .replace("$a", "attachment").replace("$c", "handler").replace("$j", ", A attachment, CompletionHandler<V, ? super A> handler");
        }
        return r;
    }

    public static String generic1(int n) {
        String r = "";

        String s = "" +
                "    public static <V, A, T $p> Assync<V> call(T obj, Wrapper$nA<V, T $p> wrapper $h ) {     \n" +
                "        return new AssyncImpl<V, A, T>(obj, wrapper, new Object[]{$q});                     \n" +
                "    }                                                                                       \n" +
                "                                                                                            \n";
        if (n == 0) {
            r += s.replace("$n", n + "").replace("$p", "").replace("$q", "").replace("$h", "")
                    .replace("$a", "null").replace("$c", "null").replace("$j", "");
            return r;
        }

        String p = "";
        for (int i = 0; i < n; i++) {
            p += "," + "P" + i;
        }

        String q = "";
        for (int i = 0; i < n; i++) {
            q += "p" + i + ",";
        }
        q = q.substring(0, q.length() - 1);

        for (int i = 0; i < 2 << n - 1; i++) {
            String h = "";
            for (int j = 0; j < n; j++) {
                if ((i & 1 << j) == 0) {
                    h += String.format(",P%d p%d", j, j);
                } else {
                    h += String.format(",Assync<? extends P%d> p%d", j, j);
                }
            }
            r += s.replace("$n", n + "").replace("$p", p).replace("$q", q).replace("$h", h)
                    .replace("$a", "null").replace("$c", "null").replace("$j", "");

        }
        return r;
    }

    public static void main(String[] args) throws Throwable {

        for (int i = 0; i <= 5; i++) {
            System.out.println(generic1(i));
        }


        CompletionHandler<Integer, Long> ch = new CompletionHandler<Integer, Long>() {
            @Override
            public void completed(Integer result, Long attachment) {
                System.out.println("completed : " + result);
            }

            @Override
            public void failed(Throwable exc, Long attachment) {
                System.out.println("failed: ");
                //exc.printStackTrace();
            }
        };

        Wrapper<Integer, Abc> w1 = Wrappers.get(es, new Callablex<Integer, Abc>() {
            @Override
            public Integer call(Abc obj, Object[] args) throws Exception {
                return obj.a((Integer) args[0], (Integer) args[1]);
            }
        });


//        Wrapper2A<Integer, AsynchronousFileChannel, ByteBuffer, Long> read = new Wrapper2A<Integer, AsynchronousFileChannel, ByteBuffer, Long>() {
//            @Override
//            public <A> void call(AsynchronousFileChannel afc, ByteBuffer dst, Long position, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
//                afc.read(dst, position, attachment, new java.nio.channels.CompletionHandler<Integer, A>() {
//                    @Override
//                    public void completed(Integer result, A attachment) {
//                        handler.completed(result, attachment);
//                    }
//
//                    @Override
//                    public void failed(Throwable exc, A attachment) {
//                        handler.failed(exc, attachment);
//                    }
//                });
//            }
//        };

        Wrapper<Integer, Abc> AbcA = new Wrapper<Integer, Abc>() {
            @Override
            public <A> void call(final Abc obj, final Object[] args, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
                es.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            int a = obj.a((Integer) args[0], (Integer) args[1]);
                            handler.completed(a, attachment);
                        } catch (Throwable t) {
                            handler.failed(t, attachment);
                        }
                        return null;
                    }
                });
            }
        };

        Abc abc = new Abc();
        Integer abcc = 1;

        Assync<Integer> g = Assyncs.run(abc, Wrappers.<Integer, Abc>get(es, Abc.class, "a"), new Object[]{0, 3});
        Assync<Integer> k = Assyncs.run(abc, AbcWrapper.a(), new Object[]{0, 3});
        Assync<Integer> s = Assyncs.run(abc, AbcA, new Object[]{0, 3});
        final Assync<Integer> p = Assyncs.run(abc, AbcA, new Object[]{s, s});
        p.register(new Runnable() {
            @Override
            public void run() {
                System.out.println("ddd" + p.result());
            }
        });


        Assync<Integer> t = Assyncs.run(abc, AbcA, new Object[]{0, 3});
        Assync<Integer> b = Assyncs.run(abc, AbcA, new Object[]{7, 3});
        Assync<Integer> l = Assyncs.run(abc, AbcWrapper.a(), new Object[]{7, 3});

        Future<Integer> future = Assyncs.future(b);


        Assyncs.future(Assyncs.run(null, null, new Object[]{t, b, l}));

        System.out.println("1");
        //Assyncs.future(t, b);
        System.out.println("2");

        System.out.println(future.get());


        //Assync<Integer> c0 = Assyncs.call(abc, AbcWrapper.a2, 1, 1);
        //Assync<Integer> c1 = Assyncs.call(abc, AbcWrapper.a3, c0, c0);

        //Assyncs.done(c0, c1);

        //System.out.println(future.get());

        es.shutdown();
    }
}
