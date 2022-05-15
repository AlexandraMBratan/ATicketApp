package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityAdminEventsListPageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminEventsListPageActivity extends AdminNavDrawerActivity{

    ActivityAdminEventsListPageBinding activityAdminEventsListPageBinding;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    AdminAdapter adminAdapter;
    List<Event> eventAdminList;
    androidx.appcompat.widget.SearchView searchEvent;
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
        //String id= mRef.getKey();

        recyclerView = findViewById(R.id.eventslist_admin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateEvent = (Button) findViewById(R.id.updateEventAdmin);
        deleteEvent = (Button) findViewById(R.id.deleteEventAdmin);

        eventAdminList = new ArrayList<Event>();
        adminAdapter = new AdminAdapter(AdminEventsListPageActivity.this,eventAdminList);
        recyclerView.setAdapter(adminAdapter);

        searchEvent = findViewById(R.id.searchEvent_admin);
        searchEvent.clearFocus();
        searchEvent.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


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

        searchEvent.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterEvent(newText);
                //setOnClickListner();
                return true;
            }
        });

    }


    private void filterEvent(String text) {
        List<Event> filteredEventList = new ArrayList<>();
        for(Event itemEvent : eventAdminList){
            if(itemEvent.getNumeEveniment().toLowerCase().contains(text.toLowerCase())){
                filteredEventList.add(itemEvent);
            }else {
                if (itemEvent.getArtist().toLowerCase().contains(text.toLowerCase())) {
                    filteredEventList.add(itemEvent);
                }else{
                    if(itemEvent.getLocatie().toLowerCase().contains(text.toLowerCase())){
                        filteredEventList.add(itemEvent);
                    }
                }
            }
        }
        if(filteredEventList.isEmpty()){
            Toast.makeText(AdminEventsListPageActivity.this, "Nu a fost gasit utilizatorul", Toast.LENGTH_LONG).show();
        }else {
            adminAdapter.filterEventList(filteredEventList);
        }
    }

}