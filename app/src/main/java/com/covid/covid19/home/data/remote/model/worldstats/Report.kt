package com.covid.covid19.home.data.remote.model.worldstats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Report(
    @Expose @SerializedName("active_cases")
    var activeCases: List<ActiveCase>?,
    @Expose @SerializedName("cases")
    var cases: Int?,
    @Expose @SerializedName("closed_cases")
    var closedCases: List<ClosedCase>?,
    @Expose @SerializedName("deaths")
    var deaths: Int?,
    @Expose @SerializedName("recovered")
    var recovered: Int?,
    @Expose @SerializedName("table")
    var table: List<List<Table>>?
) : Parcelable