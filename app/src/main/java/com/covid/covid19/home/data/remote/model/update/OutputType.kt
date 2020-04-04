package com.covid.covid19.home.data.remote.model.update


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class OutputType(
    @Expose @SerializedName("type")
    var type: String?
) : Parcelable

