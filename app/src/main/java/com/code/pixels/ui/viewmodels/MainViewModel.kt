package com.code.pixels.ui.viewmodels

import androidx.lifecycle.*
import com.code.pixels.data.model.PhotoItem
import com.code.pixels.data.repository.FlicksRepository
import kotlinx.coroutines.launch

class MainViewModel(private val flicksRepository: FlicksRepository) : ViewModel() {

    private val searchString: MutableLiveData<String> = MutableLiveData()
    private val apiResponse = MutableLiveData<List<PhotoItem>>()

    init {
        //set default search
        searchString.value = "earth"
    }

    val searchResponse: LiveData<List<PhotoItem>> = Transformations.switchMap(searchString, ::searchPhotos)

    private fun searchPhotos(searchTerm: String): LiveData<List<PhotoItem>> {
        viewModelScope.launch{
            val response = flicksRepository.searchPhotos(searchTerm)
            apiResponse.value = response
        }
        return apiResponse
    }

    fun search(searchTerm: String) {
        searchString.value = searchTerm
    }

}