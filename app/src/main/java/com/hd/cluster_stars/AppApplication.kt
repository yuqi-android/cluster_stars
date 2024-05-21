package com.hd.cluster_stars

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.hd.ibrarybase.image.HdGlideUtils

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/10
 **/
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openLog() //开启日志
        ARouter.openDebug()
        ARouter.init(this)
        HdGlideUtils.initGlide(this)

    }
}