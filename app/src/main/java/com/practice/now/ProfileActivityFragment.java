package com.practice.now;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practice.now.interfaces.ApiInterface;
import com.practice.now.modalClasses.Article;
import com.practice.now.modalClasses.GsonParse;
import com.practice.now.newsActivities.ApiClient;
import com.practice.now.newsActivities.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityFragment extends Fragment {

    List<Article> articleList;
    private ProgressDialog processDialog;
    ArrayList<Article> fragmentArticleList;
    public ProfileActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.category_layout_fragment,container,false);
    }

    ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
    Call<GsonParse> call = null;


    public void onEnter(View view){
        call = apiService.getEntertaiment();
        call.enqueue(new Callback<GsonParse>() {
            @Override
            public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                ArrayList<Article> articleList;
                articleList =  response.body().getArticles();
                Intent intent = new Intent(getContext() , ProfileActivity.class);
                intent.putParcelableArrayListExtra("article", articleList);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GsonParse> call, Throwable t) {

            }
        });
    }

    public void onFinan(View view){
        call = apiService.getBusiness();
    }

    public void onTech(View view){
        call = apiService.getNews();

    }

    public void onSport(View view){
        call = apiService.getSports();

    }

    public void onGame(View view){
        call = apiService.getGaming();

    }


}


