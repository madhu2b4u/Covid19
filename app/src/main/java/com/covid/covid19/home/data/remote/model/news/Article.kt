package com.covid.covid19.home.data.remote.model.news


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Article(
    @Expose @SerializedName("author")
    var author: String?,
    @Expose @SerializedName("content")
    var content: String?,
    @Expose @SerializedName("description")
    var description: String?,
    @Expose @SerializedName("publishedAt")
    var publishedAt: String?,
    @Expose @SerializedName("source")
    var source: Source?,
    @Expose @SerializedName("title")
    var title: String?,
    @Expose @SerializedName("url")
    var url: String?,
    @Expose @SerializedName("urlToImage")
    var urlToImage: String?
) : Parcelable