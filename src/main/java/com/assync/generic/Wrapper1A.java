package com.assync.generic;


import com.assync.CompletionHandler;
import com.assync.Wrapper;


public abstract class Wrapper1A<V, T, P0> implements Wrapper1<V, T, P0>, Wrapper<V, T> {
    @Override
    @SuppressWarnings("unchecked")
    public <A> void call(T obj, Object[] args, A attachment, CompletionHandler<V, ? super A> handler) {
        call(obj, (P0) args[0], attachment, handler);
    }

    @Override
    public abstract <A> void call(T obj, P0 p0, A attachment, CompletionHandler<V, ? super A> handler);
}
