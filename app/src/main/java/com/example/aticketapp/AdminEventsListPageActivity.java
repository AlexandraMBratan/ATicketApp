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

        EditText editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text){
        ArrayList<Event> filteredList = new ArrayList<>();

        for(Event item : eventAdminList){
            if(item.getNumeEveniment().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adminAdapter.filterList(filteredList);
    }

}