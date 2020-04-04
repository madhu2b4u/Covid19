package com.covid.covid19.home.data.repository

import androidx.lifecycle.LiveData
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.common.Result
import com.covid.covid19.home.data.remote.model.update.ApkData
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse

interface HomeRepository {

    suspend fun getIndiaStats(url :String): LiveData<Result<IndiaStatsResponse>>
    suspend fun getWorldStats(url :String): LiveData<Result<WorldStatsResponse>>
    suspend fun getUpdate(url :String): LiveData<Result<ApkData>>

}