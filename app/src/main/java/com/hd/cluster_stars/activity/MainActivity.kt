package com.hd.cluster_stars.activity

import android.os.Bundle
import com.hd.hd.R
import com.hd.hd.databinding.ActivityMainBinding
import com.hd.ibrarybase.component.HdBaseActivity

class MainActivity : HdBaseActivity<ActivityMainBinding>() {
    override val contentViewId: Int
        get() = R.layout.activity_main

    override fun initMain(savedInstanceState: Bundle?) {
    }

}