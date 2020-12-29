package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users (
    var nama : String? ="",
    var username : String? ="",
    var password : String? ="",
    var email : String? ="",
    var foto : String? ="",
    var nohp : String? =""
): Parcelable