package com.assync;


import com.assync.assyncs.Assyncs;
import com.assync.utils.Callablex;
import com.assync.utils.Wrappers;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tests {

    static class Abc {
        int aaa(int a, int b) {
            return a + b;
        }
    }

    @Test
    public void test0() throws Throwable {
        Assert.assertEquals(a(), b());
    }

    public int a() {
        Abc abc = new Abc();
        return abc.aaa(1, 2);
    }

    public int b() throws Throwable {
        ExecutorService es = Executors.newCachedThreadPool();
        Wrapper<Integer, Abc> methodAaa = Wrappers.get(es, new Callablex<Integer, Abc>() {
            @Override
            public Integer call(Abc obj, Object[] args) throws Exception {
                return obj.aaa((Integer) args[0], (Integer) args[1]);
            }
        });

        Abc abc = new Abc();
        Assync<Integer> v = Assyncs.invoke(abc, methodAaa, 1, 2);
        return Assyncs.future(v).get();
    }


}
