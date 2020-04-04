package com.covid.covid19.home.data.remote.model.worldstats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ClosedCase(
    @Expose @SerializedName("cases_which_had_an_outcome")
    var casesWhichHadAnOutcome: Int?,
    @Expose @SerializedName("deaths")
    var deaths: Int?,
    @Expose @SerializedName("recovered")
    var recovered: Int?
) : Parcelable