package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aticketapp.databinding.ActivityAdminHomePageBinding;

public class AdminHomePageActivity extends AdminNavDrawerActivity {

    ActivityAdminHomePageBinding activityDashbordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashbordBinding =  ActivityAdminHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityDashbordBinding.getRoot());
        setActionBarTitle("Home");
    }
}