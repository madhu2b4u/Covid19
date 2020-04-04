package com.covid.covid19.home.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.covid.covid19.home.data.remote.source.HomeRemoteDataSource
import com.covid.covid19.home.data.remote.source.HomeRemoteDataSourceImpl
import com.covid.covid19.home.data.remote.services.HomeService


@Module(includes = [HomeRemoteModule.Binders::class])
class HomeRemoteModule {


    @Module
    interface Binders {


        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: HomeRemoteDataSourceImpl
        ): HomeRemoteDataSource


    }


    @Provides
    fun providesHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)


}