package com.code.pixels.data.repository

import com.code.pixels.data.api.FlickrApiService
import com.code.pixels.data.model.PhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlicksRepositoryImpl(private val apiService: FlickrApiService) : FlicksRepository {

    override suspend fun searchPhotos(searchTerm: String): List<PhotoItem> {
        return withContext(Dispatchers.IO) {
            val response = apiService.api.search(searchTerm)
            response.photos.toPhotoItems()
        }
    }
}