package com.timecat.page.base.task;

/**
 * @author Jecelyin Peng <jecelyin@gmail.com>
 */
public interface TaskListener<T> {
    void onCompleted();
    void onSuccess(T result);
    void onError(Exception e);
}
