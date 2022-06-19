package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminNavDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    private TextView emailAdmin;

  //  @Override
  //  protected void onCreate(Bundle savedInstanceState) {
  //      super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_admin_nav_drawer);
  //  }


    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_admin_nav_drawer, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar= drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView= drawerLayout.findViewById(R.id.nav_view_admin);
        View hView = navigationView.getHeaderView(0);
        emailAdmin = (TextView) hView.findViewById(R.id.emailAdmin);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            emailAdmin.setText(user.getEmail());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_home_admin:
                Toast.makeText(AdminNavDrawerActivity.this, "Home Admin", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,AdminHomePageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_addevent_admin:
                Toast.makeText(AdminNavDrawerActivity.this, "Add Events - Admin", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,CalendarActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_eventslist_admin:
                Toast.makeText(AdminNavDrawerActivity.this, "Events List - Admin", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,AdminEventsListPageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_userslist_admin:
                Toast.makeText(AdminNavDrawerActivity.this, "Users List - Admin", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,AdminUsersListPageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_logout_admin:
                Toast.makeText(AdminNavDrawerActivity.this, "LogOut Admin", Toast.LENGTH_LONG).show();
                logOut(this);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void logOut(AdminNavDrawerActivity logout) {
        AlertDialog.Builder builder=new AlertDialog.Builder(logout);
        builder.setTitle("Deconectare");
        builder.setMessage("Sunteti sigur ca doriti sa va deconectati?");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logout.finishAffinity();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminNavDrawerActivity.this,MainActivity.class));
            }
        });
        builder.setNegativeButton("NU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void setActionBarTitle(String title){
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}