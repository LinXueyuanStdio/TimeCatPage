package com.timecat.page.base.base.mvp;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 林学渊
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020-02-08
 * @description null
 * @usage null
 */
public class RxHelper {
    public static <T> Observable<T> getObservable(@NonNull Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> safeObservable(@NonNull Observable<T> observable) {
        return getObservable(observable)
                .doOnError(Throwable::printStackTrace);
    }

    public static <T> Single<T> getSingle(@NonNull Single<T> single) {
        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
