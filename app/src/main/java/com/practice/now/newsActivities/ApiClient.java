package com.practice.now.newsActivities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 30-05-2017.
 */

public class ApiClient {

    public static final String BASE_URL= "https://newsapi.org/v1/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

    return retrofit;
    }

}
