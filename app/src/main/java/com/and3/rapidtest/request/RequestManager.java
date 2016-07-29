package com.and3.rapidtest.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by and3 on 28/07/16.
 */
public class RequestManager {
    private static Retrofit mRetrofit;

    protected static Retrofit request() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://polls.apiblueprint.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
