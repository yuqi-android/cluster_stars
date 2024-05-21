package com.hd.ibrarybase.http;

import android.util.Log;

import com.hd.ibrarybase.data.BaseObjectBean;
import com.hd.ibrarybase.event.LoginErrorEvent;
import com.hd.ibrarybase.utils.L;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/23
 * 统一处理rxjava的回调
 **/
public abstract class MySubscriber<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(T t) {
        L.d("OkHttp", t.toString());
        L.d("OkHttp code-->", ((BaseObjectBean) t).getCode() + "");
        if (((BaseObjectBean) t).getCode() == 200) {
            onSuccess(t);
        } else {

            if (((BaseObjectBean<?>) t).getCode()==1002){   //未登录
                EventBus.getDefault().post(new LoginErrorEvent());
                return;
            }
            // TODO 处理服务器返回错误code
            onError(((BaseObjectBean<?>) t).getCode(), ((BaseObjectBean<?>) t).getMsg());
        }
    }

    @Override
    public void onComplete() {
        finishLoading();
        L.d("OkHttp", "网络请求结束");
    }

    @Override
    public void onError(Throwable t) {
        finishLoading();
        //处理网络异常
        Log.d("OkHttp", "=========" + t);
    }

    protected abstract void onSuccess(T t);

    protected abstract void showLoading();

    protected abstract void finishLoading();

    protected abstract void onError(int code, String msg);
}