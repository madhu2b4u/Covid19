package com.covid.covid19

import android.content.Context
import com.covid.covid19.di.DaggerCovidAppComponent
import com.covid.covid19.notification.MyNotificationOpenedHandler
import com.covid.covid19.notification.MyNotificationReceivedHandler
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.gms.ads.MobileAds
import com.onesignal.OneSignal
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CovidApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCovidAppComponent.builder().application(this).build()
    }

    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this, "ca-app-pub-8074170810868474~3380538045")
        context = applicationContext
        Fresco.initialize(this)
        OneSignal.startInit(this)
            .setNotificationOpenedHandler(MyNotificationOpenedHandler())
            .setNotificationReceivedHandler(MyNotificationReceivedHandler())
            .init()

    }


}