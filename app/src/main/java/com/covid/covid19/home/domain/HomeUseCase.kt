package com.covid.covid19.home.domain

import androidx.lifecycle.LiveData
import com.covid.covid19.common.Result
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.news.NewsResponse
import com.covid.covid19.home.data.remote.model.update.ApkData
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse

interface HomeUseCase {

    suspend fun getIndiaStat(url : String): LiveData<Result<IndiaStatsResponse>>
    suspend fun getWorldStat(url : String): LiveData<Result<WorldStatsResponse>>
    suspend fun getUpdate(url : String): LiveData<Result<ApkData>>
    suspend fun getNews(page : Int): LiveData<Result<NewsResponse>>

}