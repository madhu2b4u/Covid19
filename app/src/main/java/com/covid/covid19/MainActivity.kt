package com.covid.covid19

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.covid.covid19.auth.SignUpActivity
import com.covid.covid19.common.BaseActivity
import com.covid.covid19.common.Utils
import com.covid.covid19.home.presentation.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils().transparentStatusAndNavigation(window)
        setSharedPref()






    }
}
