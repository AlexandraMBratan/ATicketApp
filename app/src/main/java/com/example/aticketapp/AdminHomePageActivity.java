package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aticketapp.databinding.ActivityAdminHomePageBinding;

public class AdminHomePageActivity extends AdminNavDrawerActivity {

    ActivityAdminHomePageBinding activityAdminHomePageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminHomePageBinding =  ActivityAdminHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminHomePageBinding.getRoot());
        setActionBarTitle("Home");
    }
}