package com.covid.covid19.home.data.remote.source

import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.update.ApkData
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse


interface HomeRemoteDataSource {

    suspend fun getIndiaData(url :String): IndiaStatsResponse
    suspend fun getWorldData(url : String): WorldStatsResponse
    suspend fun getUpdate(url : String): ApkData


}