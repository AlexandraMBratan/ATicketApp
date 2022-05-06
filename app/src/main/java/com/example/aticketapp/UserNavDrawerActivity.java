package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class UserNavDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayoutUser;

  //  @Override
  //   protected void onCreate(Bundle savedInstanceState) {
  //      super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_user_nav_drawer);
  //  }


    @Override
    public void setContentView(View view) {
        drawerLayoutUser = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_user_nav_drawer, null);
        FrameLayout container = drawerLayoutUser.findViewById(R.id.activityContainerUser);
        container.addView(view);
        super.setContentView(drawerLayoutUser);

        Toolbar toolbar= drawerLayoutUser.findViewById(R.id.toolbarUser);
        setSupportActionBar(toolbar);

        NavigationView navigationView= drawerLayoutUser.findViewById(R.id.nav_view_user);
        //View hView = navigationView.getHeaderView(0);
       // emailAdmin = (TextView) hView.findViewById(R.id.emailAdmin);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutUser,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayoutUser.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void setActionBarTitle(String title){
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}