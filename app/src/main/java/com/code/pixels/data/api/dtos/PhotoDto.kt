package com.code.pixels.data.api.dtos

import com.google.gson.annotations.SerializedName

data class PhotoDto (
    @SerializedName("owner")
    val owner: String,

    @SerializedName("server")
    val server: String,

    @SerializedName("ispublic")
    val ispublic: Int = 0,

    @SerializedName("isfriend")
    val isfriend: Int = 0,

    @SerializedName("farm")
    val farm: Int = 0,

    @SerializedName("id")
    val id: String,

    @SerializedName("secret")
    val secret: String,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("isfamily")
    val isfamily:Int = 0

)