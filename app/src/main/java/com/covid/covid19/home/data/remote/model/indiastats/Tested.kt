package com.covid.covid19.home.data.remote.model.indiastats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Tested(
    @Expose @SerializedName("source")
    var source: String?,
    @Expose @SerializedName("totalindividualstested")
    var totalindividualstested: String?,
    @Expose @SerializedName("totalpositivecases")
    var totalpositivecases: String?,
    @Expose @SerializedName("totalsamplestested")
    var totalsamplestested: String?,
    @Expose @SerializedName("updatetimestamp")
    var updatetimestamp: String?
) : Parcelable