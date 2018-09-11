package com.assync.generic;


import com.assync.CompletionHandler;
import com.assync.Wrapper;


public abstract class Wrapper0A<V, T> implements Wrapper0<V, T>, Wrapper<V, T> {
    @Override
    public <A> void call(T obj, Object[] args, A attachment, CompletionHandler<V, ? super A> handler) {
        call(obj, attachment, handler);
    }

    @Override
    public abstract <A> void call(T obj, A attachment, CompletionHandler<V, ? super A> handler);
}
