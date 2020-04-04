package com.covid.covid19.di

import android.app.Application
import com.covid.covid19.CovidApp
import com.covid.covid19.home.di.HomeDomainModule
import com.covid.covid19.home.di.HomePresentationModule
import com.covid.covid19.home.di.HomeRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton



@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        AppModule::class,HomePresentationModule::class,HomeRemoteModule::class,HomeDomainModule::class  ]
)
interface CovidAppComponent : AndroidInjector<CovidApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): CovidAppComponent
    }

    override fun inject(app: CovidApp)
}