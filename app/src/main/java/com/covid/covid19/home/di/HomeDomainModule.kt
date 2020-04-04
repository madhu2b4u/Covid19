package com.covid.covid19.home.di

import com.covid.covid19.home.data.repository.HomeRepository
import com.covid.covid19.home.data.repository.HomeRepositoryImpl
import com.covid.covid19.home.domain.HomeUseCaseImpl
import com.covid.covid19.home.domain.HomeUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class HomeDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: HomeRepositoryImpl
    ): HomeRepository


    @Binds
    abstract fun bindsArticlesUseCase(
        mHomeUseCase: HomeUseCaseImpl
    ): HomeUseCase


}