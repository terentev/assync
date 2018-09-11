package com.assync.generic;


import com.assync.CompletionHandler;
import com.assync.Wrapper;


public abstract class Wrapper2A<V, T, P0, P1> implements Wrapper2<V, T, P0, P1>, Wrapper<V, T> {
    @Override
    @SuppressWarnings("unchecked")
    public <A> void call(T obj, Object[] args, A attachment, CompletionHandler<V, ? super A> handler) {
        call(obj, (P0) args[0], (P1) args[1], attachment, handler);
    }

    @Override
    public abstract <A> void call(T obj, P0 p0, P1 p1, A attachment, CompletionHandler<V, ? super A> handler);
}
