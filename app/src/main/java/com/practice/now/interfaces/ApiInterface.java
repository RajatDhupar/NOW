package com.practice.now.interfaces;

import com.practice.now.modalClasses.GsonParse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 30-05-2017.
 */

public interface ApiInterface {

    @GET("articles?source=entertainment-weekly&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4")
    Call<GsonParse> getEntertaiment();

    @GET("articles?source=business-insider&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4")
    Call<GsonParse> getBusiness();

    @GET("articles?source=espn&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4")
    Call<GsonParse> getSports();

    @GET("articles?source=bbc-news&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4")
    Call<GsonParse> getNews();

    @GET("articles?source=ign&sortBy=top&apiKey=f2a2c293b8764288803b47d9e92150b4")
    Call<GsonParse> getGaming();

    }
