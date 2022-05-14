package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityAdminUsersListPageBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminUsersListPageActivity extends AdminNavDrawerActivity {

    ActivityAdminUsersListPageBinding activityAdminUsersListPageBinding;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    AdminUserAdapter adminUserAdapter;
    List<User> userAdminList;
    androidx.appcompat.widget.SearchView searchUser;

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

        searchUser = findViewById(R.id.searchUser_admin);
        searchUser.clearFocus();
        searchUser.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

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


        searchUser.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String text) {
        List<User> filteredUserList = new ArrayList<>();
        for(User itemUser : userAdminList){
            if(itemUser.getNume().toLowerCase().contains(text.toLowerCase())){
                filteredUserList.add(itemUser);
            }else {
                if (itemUser.getPrenume().toLowerCase().contains(text.toLowerCase())) {
                    filteredUserList.add(itemUser);
                }else{
                    if(itemUser.getEmail().toLowerCase().contains(text.toLowerCase())){
                        filteredUserList.add(itemUser);
                    }
                }
            }
        }
        if(filteredUserList.isEmpty()){
            Toast.makeText(AdminUsersListPageActivity.this, "Nu a fost gasit utilizatorul", Toast.LENGTH_LONG).show();
        }else {
            adminUserAdapter.filterUserList(filteredUserList);
        }
    }

}