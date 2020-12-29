package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Kursi (
    var seat : String = "",
    var travel : String = ""
) : Parcelable