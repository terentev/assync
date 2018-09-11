package com.assync.generic;


import com.assync.CompletionHandler;

public interface Wrapper0<V, T> {
    <A> void call(T obj, A attachment, CompletionHandler<V, ? super A> handler);
}