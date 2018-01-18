package com.practice.now.startUpActivities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.practice.now.R;
import com.practice.now.interfaces.ApiInterface;
import com.practice.now.modalClasses.Article;
import com.practice.now.modalClasses.GsonParse;
import com.practice.now.newsActivities.ApiClient;
import com.practice.now.newsActivities.ProfileActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectLoginActivity extends FragmentActivity {

    ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
    Call<GsonParse> call = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_login);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfileActivityFragmentt profileActivityFragmentt = new ProfileActivityFragmentt();
        fragmentTransaction.add(R.id.fragment1,profileActivityFragmentt);
        fragmentTransaction.commit();
    }

    public void onEnter(View view){
        call = apiService.getEntertaiment();
        call.enqueue(new Callback<GsonParse>() {
            @Override
            public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                ArrayList<Article> articleList;
                articleList =  response.body().getArticles();
                Intent intent = new Intent(getApplicationContext() , ProfileActivity.class);
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
