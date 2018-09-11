package com.assync.generic;


import com.assync.CompletionHandler;

public interface Wrapper3<V, T, P0, P1, P2> {
    <A> void call(T obj, P0 p0, P1 p1, P2 p2, A attachment, CompletionHandler<V, ? super A> handler);
}