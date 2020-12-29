package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bus (
    var kursi : String?="" ,
    var harga : String?="",
    var travel : String? ="",
    var fasilitas : String? ="",
    var foto : String? =""
): Parcelable