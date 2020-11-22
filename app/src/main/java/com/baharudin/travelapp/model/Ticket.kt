package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticket (
    var username : String ="",
    var tujuanAwal : String ? ="",
    var tujuanAkhir : String? ="",
    var tempatAwal : String? ="",
    var tempatAkhir : String? ="",
    var tanggal : String? =""
):Parcelable