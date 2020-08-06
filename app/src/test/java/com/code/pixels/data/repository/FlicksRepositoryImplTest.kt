package com.code.pixels.data.repository

import com.code.pixels.data.api.FlickrApiService
import com.code.pixels.data.api.dtos.ApiResponse
import com.code.pixels.data.api.dtos.PhotoDto
import com.code.pixels.data.api.dtos.PhotosDto
import com.code.pixels.data.model.PhotoItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class FlicksRepositoryImplTest  {

    @MockK
    lateinit var apiService: FlickrApiService

    @MockK
    lateinit var flicksRepository: FlicksRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.flicksRepository = FlicksRepositoryImpl(apiService)
    }

    @Test
    fun testExecute_Positive() = runBlocking {

        coEvery { apiService.api.search("test") } returns ApiResponse(PhotosDto(photoDtoList = listOf<PhotoDto>(PhotoDto(owner = "a",
                id = "aaa", secret = "secret", title = "Title1", server = "server", farm = 23
        )
                , PhotoDto(owner = "b",
                id = "bbb", secret = "secret", title = "Title2", server = "server", farm = 45
        ))))

        val list = flicksRepository.searchPhotos("test")

        assertEquals(list.size, 2)
        assert (list[0] is PhotoItem)
        assert (list[1] is PhotoItem)

        assertEquals ("Title1", list[0].title)
        assertEquals("Title2", list[1].title)

        val thumbNailUrl1 = "https://farm23.staticflickr.com/server/aaa_secret_m.jpg"
        val thumbNailUrl2 = "https://farm45.staticflickr.com/server/bbb_secret_m.jpg"

        val photoUrl1 = "https://farm23.staticflickr.com/server/aaa_secret_b.png"
        val photoUrl2 = "https://farm45.staticflickr.com/server/bbb_secret_b.png"


        assertEquals (thumbNailUrl1, list[0].thumbnailUrl)
        assertEquals(thumbNailUrl2, list[1].thumbnailUrl)
        assertEquals (photoUrl1, list[0].photoUrl)
        assertEquals(photoUrl2, list[1].photoUrl)
    }



}