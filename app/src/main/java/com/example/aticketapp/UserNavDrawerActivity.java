package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserNavDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayoutUser;
    private TextView numeUser, prenumeUser, emailUser;
    private DatabaseReference reference;
    private String userId;

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
        View hView = navigationView.getHeaderView(0);
        numeUser = (TextView) hView.findViewById(R.id.numeUser);
        prenumeUser = (TextView) hView.findViewById(R.id.prenumeUser);
        emailUser = (TextView) hView.findViewById(R.id.emailUser);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutUser,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayoutUser.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile!=null){
                   // String nume = userProfile.nume;
                   // String prenume = userProfile.prenume;
                   // String email = userProfile.email;

                    numeUser.setText(userProfile.nume);
                    prenumeUser.setText(userProfile.prenume);
                    emailUser.setText(userProfile.email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserNavDrawerActivity.this, "Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayoutUser.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_home_user:
                Toast.makeText(UserNavDrawerActivity.this, "Home User", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,UserHomePageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_myevents_user:
                Toast.makeText(UserNavDrawerActivity.this, "My Events - User", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,AdminHomePageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_profil_user:
                Toast.makeText(UserNavDrawerActivity.this, "Profile User", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,AdminHomePageActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_logout_user:
                Toast.makeText(UserNavDrawerActivity.this, "LogOut User", Toast.LENGTH_LONG).show();
                //logOut(this);
                break;
        }
        drawerLayoutUser.closeDrawer(GravityCompat.START);
        return false;
    }

    public void setActionBarTitle(String title){
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}