package com.assync;


public interface Wrapper<V, T> {
    <A> void call(T obj, Object[] args, A attachment, CompletionHandler<V, ? super A> handler);
}