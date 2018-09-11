package com.assync.generic;


import com.assync.CompletionHandler;

public interface Wrapper4<V, T, P0, P1, P2, P3> {
    <A> void call(T obj, P0 p0, P1 p1, P2 p2, P3 p3, A attachment, CompletionHandler<V, ? super A> handler);
}