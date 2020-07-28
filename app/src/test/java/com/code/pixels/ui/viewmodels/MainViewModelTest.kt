package com.code.pixels.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.code.pixels.data.api.Resource
import com.code.pixels.data.model.PhotoItem
import com.code.pixels.data.repository.FlicksRepository
import com.jraska.livedata.test
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest  {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: FlicksRepository

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel = MainViewModel(repository)

    }

    @After
    fun cleanup(){
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchRepositories_Positive() {

        coEvery {  repository.searchPhotos("earth")  } returns
                Resource.success(constructPhotoItems())

        mainViewModel.searchResponse.test().assertHasValue().map{ it!!.data!![0].title}.assertValue("photo1")
        mainViewModel.searchResponse.test().assertHasValue().map{ it!!.data!![0].thumbnailUrl}.assertValue("https://photo1.thumbnail.jpg")
        mainViewModel.searchResponse.test().assertHasValue().map{ it!!.data!![0].photoUrl}.assertValue("https://photo1.png")


        mainViewModel.searchResponse.test().assertHasValue().map{ it!!.data!![1].title}.assertValue("photo2")
        mainViewModel.searchResponse.test().assertHasValue().map{ it!!.data!![1].thumbnailUrl}.assertValue("https://photo2.thumbnail.jpg")
        mainViewModel.searchResponse.test().assertHasValue().map{ it!!.data!![1].photoUrl}.assertValue("https://photo2.png")

        mainViewModel.search("earth")

    }


    private fun constructPhotoItems(): List<PhotoItem> {
        val photoItem1 = PhotoItem(
                "photo1",
                "https://photo1.thumbnail.jpg",
                "https://photo1.png"
        )
        val photoItem2 = PhotoItem(
                "photo2",
                "https://photo2.thumbnail.jpg",
                "https://photo2.png"
        )

        val arrayList = ArrayList<PhotoItem>()
        arrayList.add(photoItem1)
        arrayList.add(photoItem2)
        return arrayList
    }
}