package com.practice.now.startUpActivities;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practice.now.R;
import com.practice.now.interfaces.ApiInterface;
import com.practice.now.modalClasses.GsonParse;
import com.practice.now.newsActivities.ApiClient;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityFragmentt extends Fragment {

    ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
    Call<GsonParse> call = null;

    public ProfileActivityFragmentt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile_activity_fragmentt, container, false);
    }

    
}
