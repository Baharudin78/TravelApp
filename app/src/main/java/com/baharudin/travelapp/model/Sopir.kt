package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sopir(
    var namaSopir : String = "",
    var nohp : String = "",
    var foto : String = ""
) : Parcelable