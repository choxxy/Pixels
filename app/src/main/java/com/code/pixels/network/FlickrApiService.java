package com.code.pixels.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.code.pixels.network.FlickrApi.BASE_URL;

public class FlickrApiService {

    public FlickrApi getApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(FlickrApi.class);
    }

    private OkHttpClient getClient(){

        OkHttpClient.Builder builder =  new OkHttpClient.Builder();
        return  builder.build();
    }

}
