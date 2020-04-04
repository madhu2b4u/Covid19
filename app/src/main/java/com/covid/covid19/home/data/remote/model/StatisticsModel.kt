package com.covid.covid19.home.data.remote.model

data class StatisticsModel(
    var totalDeath: String?, var totalActive: String?, var totalRecovered: String?, var countryName:String?, var totalCases: String?, var id:Int, var lastTime: String?
)