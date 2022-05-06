package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aticketapp.databinding.ActivityUserHomePageBinding;

public class UserHomePageActivity extends UserNavDrawerActivity {

    ActivityUserHomePageBinding activityUserHomePageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserHomePageBinding = ActivityUserHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityUserHomePageBinding.getRoot());
        setActionBarTitle("Home");
    }

}