package com.code.pixels.data.api.dtos

import com.google.gson.annotations.SerializedName


data class ApiResponse( @SerializedName("photos") val photos: PhotosDto)