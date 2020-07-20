package com.code.pixels.data.api

import com.code.pixels.data.api.dtos.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {


    @GET("?method=flickr.photos.search")
    suspend  fun search(@Query("text") query:String): ApiResponse

    companion object {
        const val BASE_URL = "https://www.flickr.com/services/rest/"
    }
}