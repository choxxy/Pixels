package com.code.pixels.data.api.converters;

import com.code.pixels.data.api.dtos.ApiResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class StringConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return JsonConverter.INSTANCE;
    }

    final static class JsonConverter implements Converter<ResponseBody, ApiResponse> {
        static final JsonConverter INSTANCE = new JsonConverter();

        @Override
        public ApiResponse convert(@NotNull ResponseBody responseBody) throws IOException {
            //Convert string response to json string

            try {
                String response = responseBody.string();
                //remove unwanted prefix strings
                response = response.replace("jsonFlickrApi(","");
                //remove trailing closing bracket
                response = response.substring(0, response.length() - 1);

                Gson gson = new Gson();
                ApiResponse apiResponse = gson.fromJson(response, ApiResponse.class);
                return apiResponse;

            } catch (Exception e) {
                throw new IOException("Failed to parse JSON", e);
            }
        }
    }
}