package com.lisa.politieapp.classes

import android.os.Parcel
import android.os.Parcelable

class WantedReport(val titel: String?, val publicatiedatum: String?, var afbeeldingurl : String?, val introductie: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "$titel $publicatiedatum $afbeeldingurl $introductie"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titel)
        parcel.writeString(publicatiedatum)
        parcel.writeString(afbeeldingurl)
        parcel.writeString(introductie)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WantedReport> {
        override fun createFromParcel(parcel: Parcel): WantedReport {
            return WantedReport(parcel)
        }

        override fun newArray(size: Int): Array<WantedReport?> {
            return arrayOfNulls(size)
        }
    }
}