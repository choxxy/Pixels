package com.code.pixels.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoItem(val title:String, val thumbnailUrl: String, val photoUrl: String) : Parcelable