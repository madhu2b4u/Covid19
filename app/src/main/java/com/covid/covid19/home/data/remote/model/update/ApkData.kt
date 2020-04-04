package com.covid.covid19.home.data.remote.model.update


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ApkData(
    @Expose @SerializedName("baseName")
    var baseName: String?,
    @Expose @SerializedName("enabled")
    var enabled: Boolean?,
    @Expose @SerializedName("fullName")
    var fullName: String?,
    @Expose @SerializedName("outputFile")
    var outputFile: String?,
    @Expose @SerializedName("splits")
    var type: String?,
    @Expose @SerializedName("versionCode")
    var versionCode: Int?,
    @Expose @SerializedName("versionName")
    var versionName: String?
) : Parcelable