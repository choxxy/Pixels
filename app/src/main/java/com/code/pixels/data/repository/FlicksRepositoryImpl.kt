package com.code.pixels.data.repository

import com.code.pixels.data.api.FlickrApiService
import com.code.pixels.data.api.Resource
import com.code.pixels.data.model.PhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlicksRepositoryImpl(private val apiService: FlickrApiService) : FlicksRepository {

    override suspend fun searchPhotos(searchTerm: String): Resource<List<PhotoItem>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.api.search(searchTerm)
                 Resource.success(response.body()?.photos?.toPhotoItems())
            } catch (e: Exception) {
                Resource.error(e.message!!, null)
            }
        }
    }
}