package com.covid.covid19.home.domain

import com.covid.covid19.home.data.repository.HomeRepository
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(private val mHomeRepository: HomeRepository) :
    HomeUseCase {
    override suspend fun getIndiaStat(
        url: String)=
        mHomeRepository.getIndiaStats(url)



    override suspend fun getWorldStat(url: String)= mHomeRepository.getWorldStats(url)

    override suspend fun getUpdate(url: String) = mHomeRepository.getUpdate(url)

    override suspend fun getNews(page: Int)= mHomeRepository.getNews(page)

}
