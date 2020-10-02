package com.baharudin.travelapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticket (
    var nama : String ? = "",
    var umur : String ? ="",
    var tujuanAwal : String ? ="",
    var tujuanAkhir : String? ="",
    var tempatAwal : String? ="",
    var tempatAkhir : String? ="",
    var tanggal : String? ="",
    var kursi : String? ="",
    var travel : String? =""
):Parcelable