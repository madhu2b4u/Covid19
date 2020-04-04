package com.covid.covid19.home.data.remote.model.worldstats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WorldStatsResponse(
    @Expose @SerializedName("reports")
    var reports: List<Report>?
) : Parcelable