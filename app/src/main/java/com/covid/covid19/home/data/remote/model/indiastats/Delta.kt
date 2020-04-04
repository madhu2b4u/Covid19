package com.covid.covid19.home.data.remote.model.indiastats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Delta(
    @Expose @SerializedName("active")
    var active: Int?,
    @Expose @SerializedName("confirmed")
    var confirmed: Int?,
    @Expose @SerializedName("deaths")
    var deaths: Int?,
    @Expose @SerializedName("recovered")
    var recovered: Int?
) : Parcelable