package com.assync.generic;

public interface Callablex4<V, T, P0, P1, P2, P3> {
    V call(T obj, P0 p0, P1 p1, P2 p2, P3 p3) throws Throwable;
}
