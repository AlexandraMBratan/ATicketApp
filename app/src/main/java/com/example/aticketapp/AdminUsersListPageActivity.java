package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

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
    ArrayList<User> userAdminList;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

        MenuItem searchItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adminUserAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}