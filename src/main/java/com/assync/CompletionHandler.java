package com.assync;


public interface CompletionHandler<V, A> {

    void completed(V result, A attachment);

    void failed(Throwable exc, A attachment);
}