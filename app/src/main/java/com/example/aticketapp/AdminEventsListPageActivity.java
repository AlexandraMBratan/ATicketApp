package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.aticketapp.databinding.ActivityAdminEventsListPageBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AdminEventsListPageActivity extends AdminNavDrawerActivity{

    ActivityAdminEventsListPageBinding activityAdminEventsListPageBinding;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    AdminAdapter adminAdapter;
    List<Event> eventAdminList;
    Button updateEvent, deleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminEventsListPageBinding = ActivityAdminEventsListPageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminEventsListPageBinding.getRoot());
        setActionBarTitle("Event List");


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Evenimente");
        mStorage = FirebaseStorage.getInstance();

        recyclerView = findViewById(R.id.eventslist_admin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateEvent = (Button) findViewById(R.id.updateEventAdmin);
        deleteEvent = (Button) findViewById(R.id.deleteEventAdmin);

        eventAdminList = new ArrayList<Event>();
        adminAdapter = new AdminAdapter(AdminEventsListPageActivity.this,eventAdminList);
        recyclerView.setAdapter(adminAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Event event = snapshot.getValue(Event.class);
                eventAdminList.add(event);
                adminAdapter.notifyDataSetChanged();
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