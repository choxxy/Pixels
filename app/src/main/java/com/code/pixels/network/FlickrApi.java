package com.code.pixels.network;

import com.code.pixels.network.dtos.Photos;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FlickrApi {

    public static final String BASE_URL = "https://www.flickr.com/services/rest/";
    //?method=flickr.galleries.getPhotos&api_key=1508443e49213ff84d566777dc211f2a&gallery_id=66911286-72157647277042064&per_page=5&format=json&nojsoncallback=1

    @GET("")
    Call<Photos>  search(@QueryMap Map<String,String> queries);

    @GET("")
    Call<Photos>  getThumbnail(@QueryMap Map<String,String> queries);

    @GET("")
    Call<Photos>  getPhoto(@QueryMap Map<String,String> queries);
}
