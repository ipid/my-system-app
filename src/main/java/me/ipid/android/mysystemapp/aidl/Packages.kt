package me.ipid.android.mysystemapp.aidl

import android.os.Parcel
import android.os.Parcelable

class Packages : Parcelable {
    var packages: List<String>

    constructor(packages: List<String>) {
        this.packages = packages
    }

    constructor(parcel: Parcel) {
        packages = parcel.createStringArrayList()!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeStringList(packages)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Packages> = object : Parcelable.Creator<Packages> {
            override fun createFromParcel(parcel: Parcel): Packages {
                return Packages(parcel)
            }

            override fun newArray(size: Int): Array<Packages?> {
                return arrayOfNulls(size)
            }
        }
    }
}