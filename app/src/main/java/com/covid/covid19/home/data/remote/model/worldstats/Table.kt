package com.covid.covid19.home.data.remote.model.worldstats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Table(
    @Expose @SerializedName("ActiveCases")
    var activeCases: String?,
    @Expose @SerializedName("Country")
    var country: String?,
    @Expose @SerializedName("Deaths_1M_pop")
    var deaths1MPop: String?,
    @Expose @SerializedName("FirstCase")
    var firstCase: String?,
    @Expose @SerializedName("NewCases")
    var newCases: String?,
    @Expose @SerializedName("NewDeaths")
    var newDeaths: String?,
    @Expose @SerializedName("Serious_Critical")
    var seriousCritical: String?,
    @Expose @SerializedName("TotCases_1M_Pop")
    var totCases1MPop: String?,
    @Expose @SerializedName("TotalCases")
    var totalCases: String?,
    @Expose @SerializedName("TotalDeaths")
    var totalDeaths: String?,
    @Expose @SerializedName("TotalRecovered")
    var totalRecovered: String?
) : Parcelable