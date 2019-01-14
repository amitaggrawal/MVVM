package com.amitaggrawal.thefactore.interfaces;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public interface TaskListener<T> {

    void onStarted();

    void onFinished(T result);
}
