# assync

export from https://code.google.com/archive/p/assyncs/source/default/source

Asynchronous function call in Synchronous manner

Help working with async codes

Functionality 1. usage as sync manner 1. get result in sync or async manner 1. wait N tasks 1. get Future object 1. generic support

Compare the two examples

Sync int a = abc.a(1, 1); int b = abc.a(2, 2); int c = abc.a(a, b); Async Assync<Integer> a = Assyncs.call(abc, wrapperForMethod, 1, 1); //non blocking Assync<Integer> b = Assyncs.call(abc, wrapperForMethod, 2, 2); //non blocking Assync<Integer> c = Assyncs.call(abc, wrapperForMethod, a, b); //non blocking

Full example:

``` public class Main2 {

static class Abc {
    public int a(int a, int b) {
        return a + b;
    }
}

public static void syncEquivalent() {
    Abc abc = new Abc();

    int c0 = abc.a(1, 1);
    int c1 = abc.a(2, 2);
    int c2 = abc.a(c0, c1);
}

public static void main(String[] args) throws Throwable {

    Abc abc = new Abc();

    ExecutorService es = Executors.newCachedThreadPool();  //thread pool for run our methods

    /* create assync wrapper for class Abc and method a */
    Wrapper2A<Integer, Abc, Integer, Integer> wrapperForMethod = Wrappers.get(es, new Callablex2<Integer, Abc, Integer, Integer>() {
        @Override
        public Integer call(Abc obj, Integer a, Integer b) throws Throwable {
            return obj.a(a, b);
        }
    });

    /* actual code */
    Assync<Integer> a = Assyncs.call(abc, wrapperForMethod, 1, 1);   // run in thread pool es method "a" of object abc with params 1 + 1 = 2
    Assync<Integer> b = Assyncs.call(abc, wrapperForMethod, 2, 2);   // run in thread pool es method "a" of object abc with params 2 + 2 = 4
    Assync<Integer> c = Assyncs.call(abc, wrapperForMethod, a, b);   // wait for a and b and compute c

    /* asynchronously get result */
    {
        Assyncs.handler(c, es, 13L, new CompletionHandler<Integer, Long>() {
            @Override
            public void completed(Integer result, Long attachment) {
                System.out.println("1: result= " + result + " attachment= " + attachment);
            }

            @Override
            public void failed(Throwable exc, Long attachment) {
                //if fail run this
            }
        });   // when c complete invoke CompletionHandler
    }


    /* synchronously get result */
    {
        Assyncs.done(c); // wait for c

        Future<Integer> future = Assyncs.future(c); // get Future if need

        System.out.println("2: result= " + c.result() + " future= " + future.get());
    }


    es.shutdown();

    /*
    Console output:
    1: result= 6 attachment= 13
    2: result= 6 future= 6
    */
}
}

