package com.practice.now.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.practice.now.interfaces.ApiInterface;
import com.practice.now.modalClasses.Article;
import com.practice.now.modalClasses.GsonParse;
import com.practice.now.newsActivities.ApiClient;
import com.practice.now.newsActivities.ProfileActivity;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 09-06-2017.
 */

public class AutoRefreshService extends IntentService {

    public static final String NAV_ACTIVITY_SELECTED = "navActivitySelected";
    public static final String INTENT_OUT_DATA = "intentOutData";
    public String data2 = "1";

    public AutoRefreshService() {
        super("AutoRefreshService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        String data = intent.getStringExtra(NAV_ACTIVITY_SELECTED);
//      SystemClock.sleep(7000);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GsonParse> call = null;
        //int time = intent.getIntExtra("InitialTime",0);



            if(Objects.equals(data, "enter")) {
                call = apiService.getEntertaiment();
            } else if (Objects.equals(data, "finan")) {
                call = apiService.getBusiness();
            } else if (Objects.equals(data, "game")) {
                call = apiService.getGaming();
            } else if (Objects.equals(data, "sport")) {
                call = apiService.getSports();
            } else if (Objects.equals(data, "tech")) {
                call = apiService.getNews();
            }

            call.enqueue(new Callback<GsonParse>() {
                @Override
                public void onResponse(Call<GsonParse> call, Response<GsonParse> response) {
                    ArrayList<Article> articleList;
                    articleList = response.body().getArticles();
                    Intent dataSendIntent = new Intent();
                    dataSendIntent.setAction(ProfileActivity.ServiceStateReceiver.ACTION_RESP);
                    dataSendIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    dataSendIntent.putParcelableArrayListExtra(INTENT_OUT_DATA, articleList);
                    sendBroadcast(dataSendIntent);
                }

                @Override
                 public void onFailure(Call<GsonParse> call, Throwable t) {
                }
            });
        //time = (int) System.currentTimeMillis();

    }
}
