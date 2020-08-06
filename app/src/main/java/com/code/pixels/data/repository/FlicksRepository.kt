package com.code.pixels.data.repository

import com.code.pixels.data.model.PhotoItem

interface FlicksRepository {
    suspend fun searchPhotos(searchTerm: String): List<PhotoItem>
}