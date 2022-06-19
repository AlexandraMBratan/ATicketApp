package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class UserEventsPageActivity extends AppCompatActivity {

    FirebaseDatabase mDatabaseEventUser;
    DatabaseReference mRefEventUser;
    FirebaseStorage mStorageEventUser;
    RecyclerView recyclerViewEventUser;
    UserEventsByCategoryAdapter userEventsByCategoryAdapter;
    List<Event> eventsByCategoryUserList;
    androidx.appcompat.widget.SearchView searchEventByCategory;
    String denumireCategorie="";
    UserEventsByCategoryAdapter.RecyclerViewInterface listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        mDatabaseEventUser = FirebaseDatabase.getInstance();
        mRefEventUser = mDatabaseEventUser.getReference().child("Evenimente");
        mStorageEventUser = FirebaseStorage.getInstance();

        recyclerViewEventUser = (RecyclerView) findViewById(R.id.events_by_category_user);
        recyclerViewEventUser.setHasFixedSize(true);
        recyclerViewEventUser.setLayoutManager(new LinearLayoutManager(this));

        setOnClickListner();

        eventsByCategoryUserList = new ArrayList<Event>();
        userEventsByCategoryAdapter= new UserEventsByCategoryAdapter(UserEventsPageActivity.this,eventsByCategoryUserList, listener);
        recyclerViewEventUser.setAdapter(userEventsByCategoryAdapter);

        searchEventByCategory = findViewById(R.id.searchEventByCategory_user);
        searchEventByCategory.clearFocus();
        searchEventByCategory.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        if(getIntent() != null) {
            denumireCategorie = getIntent().getStringExtra("denumire");
        }

        if(denumireCategorie!=null && !denumireCategorie.isEmpty()){
            mRefEventUser.orderByChild("tip").equalTo(denumireCategorie).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Event event= snapshot.getValue(Event.class);
                    eventsByCategoryUserList.add(event);
                    userEventsByCategoryAdapter.notifyDataSetChanged();
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
    private void setOnClickListner() {
        listener = new UserEventsByCategoryAdapter.RecyclerViewInterface() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getApplicationContext(), UserEventDetailsPageActivity.class);
                i.putExtra("numeEveniment", eventsByCategoryUserList.get(position).getNumeEveniment());
                i.putExtra("artist", eventsByCategoryUserList.get(position).getArtist());
                i.putExtra("data", eventsByCategoryUserList.get(position).getData());
                i.putExtra("ora", eventsByCategoryUserList.get(position).getOra());
                i.putExtra("locatie", eventsByCategoryUserList.get(position).getLocatie());
                i.putExtra("pret", eventsByCategoryUserList.get(position).getPret());
                i.putExtra("descriere", eventsByCategoryUserList.get(position).getDescriere());
                i.putExtra("idEvent", eventsByCategoryUserList.get(position).getIdEvent());
                i.putExtra("imagine", eventsByCategoryUserList.get(position).getImagine());
                startActivity(i);
            }
        };
    }

    private void filterEventByCategory(String text) {
        List<Event> filteredEventList = new ArrayList<>();
        for(Event itemEvent : eventsByCategoryUserList){
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
            Toast.makeText(UserEventsPageActivity.this, "Nu a fost gasit utilizatorul", Toast.LENGTH_LONG).show();
        }else {
            userEventsByCategoryAdapter.filterEventByCategoryList(filteredEventList);
        }
    }
}