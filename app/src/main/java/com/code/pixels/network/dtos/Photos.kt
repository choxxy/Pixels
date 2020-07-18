package com.code.pixels.network.dtos

import com.google.gson.annotations.SerializedName

class Photos {
    @SerializedName("stat")
    val stat: String? = null

    @SerializedName("photos")
    val photos: Photos? = null

    @SerializedName("perpage")
    val perpage = 0

    @SerializedName("total")
    val total = 0

    @SerializedName("pages")
    val pages = 0

    @SerializedName("photo")
    val photo: List<Photo>? = null

    @SerializedName("page")
    val page: String? = null

}