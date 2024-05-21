package com.hd.ibrarybase.http;

import org.reactivestreams.Publisher;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/23
 **/
public class SchedulersHelper implements FlowableTransformer {
    @Override
    public Publisher apply(Flowable upstream) {
        return  upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
