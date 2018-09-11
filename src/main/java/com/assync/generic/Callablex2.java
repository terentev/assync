package com.assync.generic;

public interface Callablex2<V, T, P0, P1> {
    V call(T obj, P0 p0, P1 p1) throws Throwable;
}
