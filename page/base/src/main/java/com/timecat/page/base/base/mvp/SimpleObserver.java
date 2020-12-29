package com.timecat.page.base.base.mvp;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020-02-08
 * @description null
 * @usage null
 */
public abstract class SimpleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
