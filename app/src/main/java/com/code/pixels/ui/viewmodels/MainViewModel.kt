package com.code.pixels.ui.viewmodels

import androidx.lifecycle.*
import com.code.pixels.data.api.Resource
import com.code.pixels.data.model.PhotoItem
import com.code.pixels.data.repository.FlicksRepository
import kotlinx.coroutines.launch

class MainViewModel(private val flicksRepository: FlicksRepository) : ViewModel() {

    private val searchString: MutableLiveData<String> = MutableLiveData()
    private val apiResponse = MutableLiveData<Resource<List<PhotoItem>>>()

    init {
        //set default search
        searchString.value = "earth"
    }

    //discard previous search Livedata
    val searchResponse: LiveData<Resource<List<PhotoItem>>> =
            Transformations.switchMap(searchString, ::searchPhotos)

    private fun searchPhotos(searchTerm: String): LiveData<Resource<List<PhotoItem>>> {
        viewModelScope.launch{
            val response = kotlin.runCatching { flicksRepository.searchPhotos(searchTerm)  }
            response.onSuccess {
                apiResponse.value = Resource.success(it)
            }
            response.onFailure {
                apiResponse.value = Resource.error(it.message.toString(),null)
            }

        }
        return apiResponse
    }

    fun search(searchTerm: String) {
        searchString.value = searchTerm
    }

}