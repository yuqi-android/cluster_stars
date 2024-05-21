package com.hd.ibrarybase.component

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.hd.ibrarybase.event.GoHomeEvent
import com.hd.ibrarybase.event.LoginErrorEvent
import com.hd.ibrarybase.http.MySubscriber
import com.hd.ibrarybase.http.RetrofitClient
import com.hd.ibrarybase.http.SchedulersHelper
import com.hd.ibrarybase.navigation.NavigationUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class HdBaseActivity<VDB : ViewDataBinding> : AppCompatActivity(), HdBaseActionInterface {
    protected abstract val contentViewId: Int

    protected abstract fun initMain(savedInstanceState: Bundle?)
    protected var mBinding: VDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        mBinding = DataBindingUtil.setContentView(this, contentViewId)
        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(true).init()
        mBinding?.lifecycleOwner = this //生命周期感应
        ViewManager.getInstance().addActivity(this)
        initMain(savedInstanceState)
        ARouter.getInstance().inject(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /**
     * 登录状态出错
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginError(event: LoginErrorEvent) {
        ViewManager.getInstance().finishAllActivity()
        NavigationUtils.goLoginActivity()
    }

    /**
     * 返回首页
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun goHome(event: GoHomeEvent) {
        ViewManager.getInstance().finishAllActivity()
        NavigationUtils.goMainActivity()
    }

}