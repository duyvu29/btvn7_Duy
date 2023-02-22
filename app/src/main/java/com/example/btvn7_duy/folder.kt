package com.example.btvn7_duy

import android.os.Parcel
import android.os.Parcelable

data class folder(val id: Int, var title: String, var content: String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<folder> {
        override fun createFromParcel(parcel: Parcel): folder {
            return folder(parcel)
        }

        override fun newArray(size: Int): Array<folder?> {
            return arrayOfNulls(size)
        }
    }


}