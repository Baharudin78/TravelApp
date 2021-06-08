package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Resto (
    val nama : String = "",
    val letak : String ="",
    val telepon : String ="",
    val foto : String =""
        ):Parcelable