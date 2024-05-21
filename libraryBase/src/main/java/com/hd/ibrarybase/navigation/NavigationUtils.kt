package com.hd.ibrarybase.navigation

import com.alibaba.android.arouter.launcher.ARouter

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/10
 * 页面跳转
 **/
class NavigationUtils {
    companion object {

        // 首页
        fun goMainActivity() {
            ARouter.getInstance().build(RouterConstant.MAIN_ACTIVITY).navigation();
        }

        fun goLoginActivity() {
            ARouter.getInstance().build(RouterConstant.LOGIN_ACTIVITY).navigation()
        }


        //心理健康测量
        fun goHealthyMeasureActivity() {
            ARouter.getInstance().build(RouterConstant.HOME_HEALTHY_MEASURE_ACTIVITY).navigation()
        }

        //文章详情页面
        fun goHomeArticleActivity(id: Int) {
            ARouter.getInstance().build(RouterConstant.HOME_ARTICLE_DETAIL_ACTIVITY)
                .withInt("id", id).navigation()
        }

        //综合测量报告
        fun goPlanReport(planId: Int) {
            ARouter.getInstance().build(RouterConstant.HOME_PLAN_REPORT_ACTIVITY)
                .withInt("planId", planId).navigation()
        }
    }
}