package com.covid.covid19.di

import com.covid.covid19.home.presentation.ui.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesStatFragment(): StatFragment

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesCovidRiskFragment(): CovidRiskFragment

    @ContributesAndroidInjector
    abstract fun contributesIndiaFragment(): IndiaFragment

    @ContributesAndroidInjector
    abstract fun contributesWorldFragmentt(): WorldFragment

    @ContributesAndroidInjector
    abstract fun contributesAboutFragment(): AboutFragment


    @ContributesAndroidInjector
    abstract fun contributesNewsFragment(): NewsFragment
}
