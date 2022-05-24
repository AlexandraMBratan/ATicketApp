package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

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
    List<Event> eventsByCategoryAdminList;
    androidx.appcompat.widget.SearchView searchEventByCategory;
    String denumireCategorie="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_events_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        mDatabaseEvent = FirebaseDatabase.getInstance();
        mRefEvent = mDatabaseEvent.getReference().child("Evenimente");
        mStorageEvent = FirebaseStorage.getInstance();

        recyclerViewEvent = (RecyclerView) findViewById(R.id.events_by_category_admin);
        recyclerViewEvent.setHasFixedSize(true);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));

        eventsByCategoryAdminList = new ArrayList<Event>();
        adminEventsByCategoryAdapter= new AdminEventsByCategoryAdapter(AdminEventsPageActivity.this,eventsByCategoryAdminList);
        recyclerViewEvent.setAdapter(adminEventsByCategoryAdapter);

        searchEventByCategory = findViewById(R.id.searchEventByCategory_admin);
        searchEventByCategory.clearFocus();
        searchEventByCategory.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


        if(getIntent() != null) {
            denumireCategorie = getIntent().getStringExtra("denumire");
        }
        if(!denumireCategorie.isEmpty() && denumireCategorie != null){
            mRefEvent.orderByChild("tip").equalTo(denumireCategorie).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Event event= snapshot.getValue(Event.class);
                    eventsByCategoryAdminList.add(event);
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

        searchEventByCategory.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterEventByCategory(newText);
                return true;
            }
        });

    }

    private void filterEventByCategory(String text) {
        List<Event> filteredEventList = new ArrayList<>();
        for(Event itemEvent : eventsByCategoryAdminList){
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
            Toast.makeText(AdminEventsPageActivity.this, "Nu a fost gasit utilizatorul", Toast.LENGTH_LONG).show();
        }else {
            adminEventsByCategoryAdapter.filterEventByCategoryList(filteredEventList);
        }
    }
}