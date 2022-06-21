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

import com.example.aticketapp.databinding.ActivityUserCloseEventsBinding;
import com.example.aticketapp.databinding.ActivityUserMyPurchasesPageBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserCloseEventsActivity extends UserNavDrawerActivity {

    FirebaseDatabase mDatabaseEvent;
    DatabaseReference mRefEvent;
    FirebaseStorage mStorageEvent;
    RecyclerView recyclerViewEvent;
    UserCloseEventsAdapter userCloseEventsAdapter;
    List<Event> closeEventsUserList;
    androidx.appcompat.widget.SearchView searchCloseEvent;
    UserCloseEventsAdapter.RecyclerViewInterface listener;
    SimpleDateFormat format;
    String data;
    Date d3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserCloseEventsBinding activityUserCloseEventsBinding = ActivityUserCloseEventsBinding.inflate(getLayoutInflater());
        setContentView(activityUserCloseEventsBinding.getRoot());
        setActionBarTitle("Evenimente apropiate");
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        mDatabaseEvent = FirebaseDatabase.getInstance();
        mRefEvent = mDatabaseEvent.getReference().child("Evenimente");
        mStorageEvent = FirebaseStorage.getInstance();

        recyclerViewEvent = (RecyclerView) findViewById(R.id.close_events_user);
        recyclerViewEvent.setHasFixedSize(true);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));

        setOnClickListner();

        closeEventsUserList = new ArrayList<Event>();
        userCloseEventsAdapter = new UserCloseEventsAdapter(UserCloseEventsActivity.this, closeEventsUserList, listener);
        recyclerViewEvent.setAdapter(userCloseEventsAdapter);

        searchCloseEvent = findViewById(R.id.searchCloseEvent_user);
        searchCloseEvent.clearFocus();
        searchCloseEvent.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        mRefEvent.orderByChild("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Event event= snapshot.getValue(Event.class);
                Calendar c = Calendar.getInstance();
                format = new SimpleDateFormat("dd/MM/yyyy");
                data = format.format(c.getTime());
                Date d1 = new Date();

                c.add(Calendar.DATE, 7);
                Date d2 = c.getTime();

                String data_event = event.getData();

                try {
                    d3 = format.parse(data_event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(d3.after(d1) && d3.before(d2)){
                    closeEventsUserList.add(event);
                    userCloseEventsAdapter.notifyDataSetChanged();
                }
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

        searchCloseEvent.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCloseEvent(newText);
                return true;
            }
        });

    }

    private void setOnClickListner() {
        listener = new UserCloseEventsAdapter.RecyclerViewInterface() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getApplicationContext(), UserEventDetailsPageActivity.class);
                i.putExtra("numeEveniment", closeEventsUserList.get(position).getNumeEveniment());
                i.putExtra("artist", closeEventsUserList.get(position).getArtist());
                i.putExtra("data", closeEventsUserList.get(position).getData());
                i.putExtra("ora", closeEventsUserList.get(position).getOra());
                i.putExtra("locatie", closeEventsUserList.get(position).getLocatie());
                i.putExtra("pret", closeEventsUserList.get(position).getPret());
                i.putExtra("descriere", closeEventsUserList.get(position).getDescriere());
                i.putExtra("idEvent", closeEventsUserList.get(position).getIdEvent());
                i.putExtra("imagine", closeEventsUserList.get(position).getImagine());
                startActivity(i);
            }
        };
    }


    private void filterCloseEvent(String text) {
        List<Event> filteredEventList = new ArrayList<>();
        for(Event itemEvent : closeEventsUserList){
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
            Toast.makeText(UserCloseEventsActivity.this, "Nu a fost gasit utilizatorul", Toast.LENGTH_LONG).show();
        }else {
            userCloseEventsAdapter.filterCloseEventList(filteredEventList);
        }
    }

}