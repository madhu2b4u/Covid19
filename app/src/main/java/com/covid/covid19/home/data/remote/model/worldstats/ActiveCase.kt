package com.covid.covid19.home.data.remote.model.worldstats


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ActiveCase(
    @Expose @SerializedName("criticalStates")
    var criticalStates: Int?,
    @Expose @SerializedName("currently_infected_patients")
    var currentlyInfectedPatients: Int?,
    @Expose @SerializedName("inMidCondition")
    var inMidCondition: Int?
) : Parcelable