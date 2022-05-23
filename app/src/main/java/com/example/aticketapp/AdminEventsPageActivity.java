package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AdminEventsPageActivity extends AppCompatActivity {

    FirebaseDatabase mDatabaseEvent;
    DatabaseReference mRefEvent;
    FirebaseStorage mStorageEvent;
    RecyclerView recyclerViewEvent;
    AdminEventsByCategoryAdapter adminEventsByCategoryAdapter;
    List<Event> eventAdminList;
    String denumireCategorie="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_events_page);

        mDatabaseEvent = FirebaseDatabase.getInstance();
        mRefEvent = mDatabaseEvent.getReference().child("Evenimente");
        mStorageEvent = FirebaseStorage.getInstance();

        recyclerViewEvent = (RecyclerView) findViewById(R.id.events_by_category_admin);
        recyclerViewEvent.setHasFixedSize(true);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));

        eventAdminList = new ArrayList<Event>();
        adminEventsByCategoryAdapter= new AdminEventsByCategoryAdapter(AdminEventsPageActivity.this,eventAdminList);
        recyclerViewEvent.setAdapter(adminEventsByCategoryAdapter);

        if(getIntent() != null) {
            denumireCategorie = getIntent().getStringExtra("denumire");
        }
        if(!denumireCategorie.isEmpty() && denumireCategorie != null){
            mRefEvent.orderByChild("tip").equalTo(denumireCategorie).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Event event= snapshot.getValue(Event.class);
                    eventAdminList.add(event);
                    adminEventsByCategoryAdapter.notifyDataSetChanged();
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
}