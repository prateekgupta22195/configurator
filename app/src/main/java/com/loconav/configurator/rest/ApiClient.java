package com.loconav.configurator.rest;

import retrofit2.Retrofit;

/**
 * Created by prateek on 16/05/18.
 */

public class ApiClient {
    public static final String BASE_URL = "https://loconav.com/api/v1/trucks/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}