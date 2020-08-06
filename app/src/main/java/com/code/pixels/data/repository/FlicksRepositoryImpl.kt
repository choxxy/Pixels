package com.code.pixels.data.repository

import com.code.pixels.data.api.FlickrApiService
import com.code.pixels.data.model.PhotoItem

class FlicksRepositoryImpl(private val apiService: FlickrApiService) : FlicksRepository {

    override suspend fun searchPhotos(searchTerm: String): List<PhotoItem> {
        //No need to change context to Dispatchers.IO as Retrofit handles that automatically.
        val response = apiService.api.search(searchTerm)
        return response.photos.toPhotoItems()

    }
}