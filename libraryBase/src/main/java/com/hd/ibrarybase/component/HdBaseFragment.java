package com.hd.ibrarybase.component;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.hd.ibrarybase.event.BaseFragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/4/7
 **/
public abstract class HdBaseFragment<VDB extends ViewDataBinding> extends Fragment {
    protected VDB mBinding = null;

    @LayoutRes
    public abstract int getLayoutId();
    public abstract void initMain();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initMain();
        return mBinding.getRoot();
    }

    /**
     * 占位的Event,如果没有会报错
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  baseEvent(BaseFragmentEvent event){

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
