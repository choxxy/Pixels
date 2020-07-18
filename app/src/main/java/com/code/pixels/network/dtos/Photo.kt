package com.code.pixels.network.dtos

import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("owner")
    val owner: String? = null

    @SerializedName("server")
    val server: String? = null

    @SerializedName("is_primary")
    val isPrimary = 0

    @SerializedName("has_comment")
    val hasComment = 0

    @SerializedName("ispublic")
    val ispublic = 0

    @SerializedName("isfriend")
    val isfriend = 0

    @SerializedName("farm")
    val farm = 0

    @SerializedName("id")
    val id: String? = null

    @SerializedName("secret")
    val secret: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("isfamily")
    val isfamily = 0

}