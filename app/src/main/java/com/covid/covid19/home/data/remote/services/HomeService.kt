package com.covid.covid19.home.data.remote.services

import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.news.NewsResponse
import com.covid.covid19.home.data.remote.model.update.UpdateResponse
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface HomeService {

    //India Stats
    @GET
    fun getIndiaStatsAsync(@Url url :String): Deferred<Response<IndiaStatsResponse>>

    //World Stats
    @GET
    fun getWorldStatsAsync(@Url url :String): Deferred<Response<WorldStatsResponse>>

    //Check Updates
    @GET
    fun getUpdate(@Url url :String): Deferred<Response<UpdateResponse>>
    @GET("everything?q=covid19&sortBy=publishedAt&apiKey=02ff2aa7041041f2b3802c30cba1aea9&language=en")
    fun getNews(@Query("pagesize") page: Int): Deferred<Response<NewsResponse>>
}