package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bus (
    var nama : String = "",
    var fasilitas : String ="",
    var foto : String ="",
    var harga : String =""
): Parcelable