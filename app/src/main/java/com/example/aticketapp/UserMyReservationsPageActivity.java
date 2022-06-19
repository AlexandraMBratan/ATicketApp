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

import com.example.aticketapp.databinding.ActivityUserMyReservationsPageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class UserMyReservationsPageActivity extends UserNavDrawerActivity {

    FirebaseDatabase mDatabaseReservationsUser;
    DatabaseReference mRefReservationsUser;
    FirebaseStorage mStorageReservationsUser;
    DatabaseReference mRefReservation;
    FirebaseAuth mAuth;
    FirebaseUser user;
    RecyclerView recyclerViewReservationsUser;
    UserMyReservationsAdapter userMyReservationsAdapter;
    List<Reservation> eventsMyReservationUserList;
    androidx.appcompat.widget.SearchView searchReservation;
    UserMyReservationsAdapter.RecyclerViewInterface listener;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserMyReservationsPageBinding activityUserMyReservationsPageBinding = ActivityUserMyReservationsPageBinding.inflate(getLayoutInflater());
        setContentView(activityUserMyReservationsPageBinding.getRoot());
        setActionBarTitle("Rezervarile mele");

        mDatabaseReservationsUser = FirebaseDatabase.getInstance();
        mRefReservationsUser = mDatabaseReservationsUser.getReference().child("Evenimente");
        mStorageReservationsUser = FirebaseStorage.getInstance();
        mRefReservation = mDatabaseReservationsUser.getReference().child("Rezervari");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        idUser = user.getUid();

        recyclerViewReservationsUser = (RecyclerView) findViewById(R.id.myReservations_user);
        recyclerViewReservationsUser.setHasFixedSize(true);
        recyclerViewReservationsUser.setLayoutManager(new LinearLayoutManager(this));

        eventsMyReservationUserList = new ArrayList<Reservation>();
        userMyReservationsAdapter = new UserMyReservationsAdapter(UserMyReservationsPageActivity.this, eventsMyReservationUserList,listener);
        recyclerViewReservationsUser.setAdapter(userMyReservationsAdapter);

        searchReservation = findViewById(R.id.searchReservation_user);
        searchReservation.clearFocus();
        searchReservation.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        mRefReservation.orderByChild("idUserRezervat").equalTo(idUser).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Reservation reservation = snapshot.getValue(Reservation.class);
                    eventsMyReservationUserList.add(reservation);
                    userMyReservationsAdapter.notifyDataSetChanged();
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

        searchReservation.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
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
        List<Reservation> filteredEventList = new ArrayList<>();
        for(Reservation reservation : eventsMyReservationUserList){
            if(reservation.getNumeEvenimentRezervat().toLowerCase().contains(text.toLowerCase())){
                filteredEventList.add(reservation);
            }else {
                if (reservation.getArtistEvenimentRezervat().toLowerCase().contains(text.toLowerCase())) {
                    filteredEventList.add(reservation);
                }else{
                    if(reservation.getLocatieEvenimentRezervat().toLowerCase().contains(text.toLowerCase())){
                        filteredEventList.add(reservation);
                    }
                }
            }
        }
        if(filteredEventList.isEmpty()){
            Toast.makeText(UserMyReservationsPageActivity.this, "Nu a fost gasita rezervarea", Toast.LENGTH_LONG).show();
        }else {
            userMyReservationsAdapter.filterMyReservationList(filteredEventList);
        }
    }

}