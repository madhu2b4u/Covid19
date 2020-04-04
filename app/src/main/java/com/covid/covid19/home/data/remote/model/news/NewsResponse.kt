package com.covid.covid19.home.data.remote.model.news


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class NewsResponse(
    @Expose @SerializedName("articles")
    var articles: List<Article>?,
    @Expose @SerializedName("status")
    var status: String?,
    @Expose @SerializedName("totalResults")
    var totalResults: Int?
) : Parcelable