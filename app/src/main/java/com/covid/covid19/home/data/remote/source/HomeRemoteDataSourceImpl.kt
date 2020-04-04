package com.covid.covid19.home.data.remote.source

import com.covid.covid19.di.qualifiers.IO
import com.covid.covid19.home.data.remote.model.news.NewsResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.covid.covid19.home.data.remote.services.HomeService


class HomeRemoteDataSourceImpl @Inject constructor(
    private val service: HomeService,
    @IO private val context: CoroutineContext
) : HomeRemoteDataSource {
    override suspend fun getIndiaData(url :String)= withContext(context) {
        val response = service.getIndiaStatsAsync(url).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no stats")
        else
            throw Exception("invalid request with code ${response.code()}")

    }

    override suspend fun getWorldData(url :String)= withContext(context) {
        val response = service.getWorldStatsAsync(url).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no stats")
        else
            throw Exception("invalid request with code ${response.code()}")

    }

    override suspend fun getUpdate(url: String)= withContext(context) {
        val response = service.getUpdate(url).await()
        if (response.isSuccessful)
            response.body()?.apkData?: throw Exception("no stats")
        else
            throw Exception("invalid request with code ${response.code()}")

    }

    override suspend fun getNews(page: Int) = withContext(context) {
        val response = service.getNews(page).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no stats")
        else
            throw Exception("invalid request with code ${response.code()}")

    }

}
