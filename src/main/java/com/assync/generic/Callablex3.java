package com.assync.generic;

public interface Callablex3<V, T, P0, P1, P2> {
    V call(T obj, P0 p0, P1 p1, P2 p2) throws Throwable;
}
