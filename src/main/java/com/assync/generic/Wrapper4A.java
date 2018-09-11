package com.assync.generic;


import com.assync.CompletionHandler;
import com.assync.Wrapper;


public abstract class Wrapper4A<V, T, P0, P1, P2, P3> implements Wrapper4<V, T, P0, P1, P2, P3>, Wrapper<V, T> {
    @Override
    @SuppressWarnings("unchecked")
    public <A> void call(T obj, Object[] args, A attachment, CompletionHandler<V, ? super A> handler) {
        call(obj, (P0) args[0], (P1) args[1], (P2) args[2], (P3) args[3], attachment, handler);
    }

    @Override
    public abstract <A> void call(T obj, P0 p0, P1 p1, P2 p2, P3 p3, A attachment, CompletionHandler<V, ? super A> handler);
}
