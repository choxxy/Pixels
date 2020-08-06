package com.code.pixels.data.api

import com.code.pixels.BuildConfig
import com.code.pixels.data.api.converters.StringConverterFactory
import com.code.pixels.data.api.interceptors.UrlInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit



class FlickrApiService {
    val api: FlickrApi
        get() {

            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(FlickrApi.BASE_URL)
                    .client(client)
                    // response from flickr is not json but text,
                    // a custom converter is required to work with retrofit
                    .addConverterFactory(StringConverterFactory())
                    .build()

            return retrofit.create(FlickrApi::class.java)
        }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()

            val logging = HttpLoggingInterceptor()

            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            if (BuildConfig.DEBUG)
                builder.addInterceptor(logging)

            builder.addInterceptor(UrlInterceptor())

            return builder.build()
        }
}