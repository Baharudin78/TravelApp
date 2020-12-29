package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination (
    var nama : String? =""
):Parcelable