package com.covid.covid19.home.data.repository


import androidx.lifecycle.liveData
import com.covid.covid19.common.Result
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.update.ApkData
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse
import com.covid.covid19.home.data.remote.source.HomeRemoteDataSource
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource) : HomeRepository {
    override suspend fun getIndiaStats(
        url: String) = liveData {
        emit(Result.loading())
        try {
            var indiaStatsResponse: IndiaStatsResponse? = null

            if (indiaStatsResponse == null) {
                indiaStatsResponse = remoteDataSource.getIndiaData(url)
            }
            emit(Result.success(indiaStatsResponse))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


    override suspend fun getWorldStats(
        url: String)= liveData {
        emit(Result.loading())
        try {
            var worldStatsResponse: WorldStatsResponse? = null

            if (worldStatsResponse == null) {
                worldStatsResponse = remoteDataSource.getWorldData(url)
            }
            emit(Result.success(worldStatsResponse))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }

    override suspend fun getUpdate(url: String)= liveData {
        emit(Result.loading())
        try {
            var updateResponse: ApkData? = null

            if (updateResponse == null) {
                updateResponse = remoteDataSource.getUpdate(url)
            }
            emit(Result.success(updateResponse))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


}