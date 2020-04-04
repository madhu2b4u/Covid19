package com.covid.covid19.home.data.remote.model.indiastats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class KeyValue(
    @Expose @SerializedName("confirmeddelta")
    var confirmeddelta: String?,
    @Expose @SerializedName("counterforautotimeupdate")
    var counterforautotimeupdate: String?,
    @Expose @SerializedName("deceaseddelta")
    var deceaseddelta: String?,
    @Expose @SerializedName("lastupdatedtime")
    var lastupdatedtime: String?,
    @Expose @SerializedName("recovereddelta")
    var recovereddelta: String?,
    @Expose @SerializedName("statesdelta")
    var statesdelta: String?
) : Parcelable