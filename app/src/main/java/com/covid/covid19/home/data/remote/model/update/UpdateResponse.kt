package com.covid.covid19.home.data.remote.model.update


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UpdateResponse(
    @Expose @SerializedName("apkData")
    var apkData: ApkData?,
    @Expose @SerializedName("outputType")
    var outputType: OutputType?,
    @Expose @SerializedName("path")
    var path: String?
) : Parcelable