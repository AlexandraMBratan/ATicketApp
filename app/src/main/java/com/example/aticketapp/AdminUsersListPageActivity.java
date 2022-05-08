package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.aticketapp.databinding.ActivityAdminUsersListPageBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AdminUsersListPageActivity extends AdminNavDrawerActivity {

    ActivityAdminUsersListPageBinding activityAdminUsersListPageBinding;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    AdminUserAdapter adminUserAdapter;
    List<User> userAdminList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminUsersListPageBinding = ActivityAdminUsersListPageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminUsersListPageBinding.getRoot());
        setActionBarTitle("User List");

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Users");

        recyclerView = findViewById(R.id.userslist_admin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdminList = new ArrayList<User>();
        adminUserAdapter = new AdminUserAdapter(AdminUsersListPageActivity.this,userAdminList);
        recyclerView.setAdapter(adminUserAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                userAdminList.add(user);
                adminUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}