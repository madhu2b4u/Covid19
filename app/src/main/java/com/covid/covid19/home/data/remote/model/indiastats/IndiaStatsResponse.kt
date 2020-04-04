package com.covid.covid19.home.data.remote.model.indiastats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class IndiaStatsResponse(
    @Expose @SerializedName("cases_time_series")
    var casesTimeSeries: List<CasesTimeSery>?,
    @Expose @SerializedName("key_values")
    var keyValues: List<KeyValue>?,
    @Expose @SerializedName("statewise")
    var statewise: List<Statewise>?,
    @Expose @SerializedName("tested")
    var tested: List<Tested>?
) : Parcelable