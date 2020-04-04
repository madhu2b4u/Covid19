package com.covid.covid19.home.data.remote.model.indiastats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Statewise(
    @Expose @SerializedName("active")
    var active: String?,
    @Expose @SerializedName("confirmed")
    var confirmed: String?,
    @Expose @SerializedName("deaths")
    var deaths: String?,
    @Expose @SerializedName("delta")
    var delta: Delta?,
    @Expose @SerializedName("lastupdatedtime")
    var lastupdatedtime: String?,
    @Expose @SerializedName("recovered")
    var recovered: String?,
    @Expose @SerializedName("state")
    var state: String?
) : Parcelable