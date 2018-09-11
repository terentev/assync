package com.assync.generic;


import com.assync.CompletionHandler;

public interface Wrapper2<V, T, P0, P1> {
    <A> void call(T obj, P0 p0, P1 p1, A attachment, CompletionHandler<V, ? super A> handler);
}