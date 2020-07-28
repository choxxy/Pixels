package com.code.pixels.data.api.dtos

import com.code.pixels.data.model.PhotoItem
import com.google.gson.annotations.SerializedName

data class PhotosDto(
        @SerializedName("stat")
        val stat: String = "",

        @SerializedName("perpage")
        val perpage: Int = 0,

        @SerializedName("total")
        val total: Int = 0,

        @SerializedName("pages")
        val pages: Int = 0,

        @SerializedName("photo")
        val photoDtoList: List<PhotoDto>,

        @SerializedName("page")
        val page: String = "0"
) {

    //get PhotoItem from PhotoDto
    fun toPhotoItems(): List<PhotoItem> {
        val photoItemList = mutableListOf<PhotoItem>()
        for (photo in photoDtoList) {

            if(photo.farm == 0 || photo.server == "0")
                continue

            val thumbNail = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
            val title = photo.title?: ""
            val fullImage = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.png"
            val photoItem = PhotoItem(title, thumbNail, fullImage)
            photoItemList.add(photoItem)
        }
        return photoItemList
    }
}