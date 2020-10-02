package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bus (
    var nama : String = "",
    var falitas : String ="",
    var foto : String =""
): Parcelable