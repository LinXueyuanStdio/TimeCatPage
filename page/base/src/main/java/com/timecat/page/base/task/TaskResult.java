package com.timecat.page.base.task;

/**
 * @author Jecelyin Peng <jecelyin@gmail.com>
 */
public class TaskResult<T> {
    private T result;
    private boolean waitResult;
    private boolean hasResult;
    private Exception error;

    void waitResult() throws InterruptedException {
        this.waitResult = true;
        if (!hasResult)
            synchronized (this) {
                wait();
            }
    }

    T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.hasResult = true;
        this.result = result;
        done();
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
        done();
    }

    private void done() {
        if (waitResult)
            synchronized (this) {
                notify();
            }
    }
}