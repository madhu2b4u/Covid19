package com.covid.covid19.home.data.remote.model.news


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Source(
    @Expose @SerializedName("id")
    var id: String?,
    @Expose @SerializedName("name")
    var name: String?
) : Parcelable