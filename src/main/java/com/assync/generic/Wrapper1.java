package com.assync.generic;


import com.assync.CompletionHandler;

public interface Wrapper1<V, T, P0> {
    public <A> void call(T obj, P0 p0, A attachment, CompletionHandler<V, ? super A> handler);
}