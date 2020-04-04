package com.covid.covid19.di


import com.covid.covid19.MainActivity
import com.covid.covid19.auth.SignUpActivity
import com.covid.covid19.home.presentation.ui.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSignUpctivity(): SignUpActivity

    @ContributesAndroidInjector
    internal abstract fun contributesHomeActivity(): HomeActivity
}