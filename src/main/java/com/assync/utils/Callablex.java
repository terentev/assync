package com.assync.utils;

public interface Callablex<V, T> {
    V call(T obj, Object[] args) throws Throwable;
}
