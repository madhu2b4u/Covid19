package com.covid.covid19.home.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covid.covid19.common.Result
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.update.ApkData
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse
import com.covid.covid19.home.domain.HomeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val mHomeUseCase: HomeUseCase) : ViewModel() {

    val indiaResult = MediatorLiveData<Result<IndiaStatsResponse>>()
    val worldResult = MediatorLiveData<Result<WorldStatsResponse>>()
    val updateResult = MediatorLiveData<Result<ApkData>>()


    fun loadIndia(url :String, mustFetchFromNetwork: Boolean = false) {
        viewModelScope.launch {
            indiaResult.addSource(mHomeUseCase.getIndiaStat(url)) {
                indiaResult.value = it
            }
        }
    }

    fun loadWorld(url : String ="", mustFetchFromNetwork: Boolean = false) {
        viewModelScope.launch {
            worldResult.addSource(mHomeUseCase.getWorldStat(url)) {
                worldResult.value = it
            }
        }
    }

    fun checkUpdate(url : String ="", mustFetchFromNetwork: Boolean = false) {
        viewModelScope.launch {
            updateResult.addSource(mHomeUseCase.getUpdate(url)) {
                updateResult.value = it
            }
        }
    }


}