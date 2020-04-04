package com.covid.covid19.home.data.remote.model.indiastats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CasesTimeSery(
    @Expose @SerializedName("dailyconfirmed")
    var dailyconfirmed: String?,
    @Expose @SerializedName("dailydeceased")
    var dailydeceased: String?,
    @Expose @SerializedName("dailyrecovered")
    var dailyrecovered: String?,
    @Expose @SerializedName("date")
    var date: String?,
    @Expose @SerializedName("totalconfirmed")
    var totalconfirmed: String?,
    @Expose @SerializedName("totaldeceased")
    var totaldeceased: String?,
    @Expose @SerializedName("totalrecovered")
    var totalrecovered: String?
) : Parcelable