
package com.code.pixels.data.api.interceptors;

import com.code.pixels.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UrlInterceptor implements Interceptor {

    private static final String PER_PAGE =  "25";
    private static final String FORMAT =  "json";
    private static final String SAFE_SEARCH = "1";  //safe
    private static final String CONTENT_TYPE = "1"; //photos only

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        //add  url params to every call
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("safe_search", SAFE_SEARCH)
                .addQueryParameter("per_page", PER_PAGE)
                .addQueryParameter("format", FORMAT)
                .addQueryParameter("content_type", CONTENT_TYPE)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}