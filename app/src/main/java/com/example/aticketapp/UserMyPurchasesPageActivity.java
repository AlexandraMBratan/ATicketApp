package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputType;

import com.example.aticketapp.databinding.ActivityUserMyPurchasesPageBinding;
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

public class UserMyPurchasesPageActivity extends UserNavDrawerActivity {

    FirebaseDatabase mDatabasePurchasesUser;
    DatabaseReference mRefPurchasesUser;
    FirebaseStorage mStoragePurchasesUser;
    DatabaseReference mRefPurchase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    RecyclerView recyclerViewPurchasesUser;
    UserMyPurchasesAdapter userMyPurchasesAdapter;
    List<Purchase> eventsMyPurchaseUserList;
    androidx.appcompat.widget.SearchView searchPurchase;
    UserMyPurchasesAdapter.RecyclerViewInterface listener;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserMyPurchasesPageBinding activityUserMyPurchasesPageBinding = ActivityUserMyPurchasesPageBinding.inflate(getLayoutInflater());
        setContentView(activityUserMyPurchasesPageBinding.getRoot());
        setActionBarTitle("Evenimentele mele");

        mDatabasePurchasesUser = FirebaseDatabase.getInstance();
        mRefPurchasesUser = mDatabasePurchasesUser.getReference().child("Evenimente");
        mStoragePurchasesUser = FirebaseStorage.getInstance();
        mRefPurchase = mDatabasePurchasesUser.getReference().child("Achizitii");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        idUser = user.getUid();

        recyclerViewPurchasesUser = (RecyclerView) findViewById(R.id.myPurchases_user);
        recyclerViewPurchasesUser.setHasFixedSize(true);
        recyclerViewPurchasesUser.setLayoutManager(new LinearLayoutManager(this));

        eventsMyPurchaseUserList = new ArrayList<Purchase>();
        userMyPurchasesAdapter = new UserMyPurchasesAdapter(UserMyPurchasesPageActivity.this, eventsMyPurchaseUserList,listener);
        recyclerViewPurchasesUser.setAdapter(userMyPurchasesAdapter);

        searchPurchase = findViewById(R.id.searchPurchases_user);
        searchPurchase.clearFocus();
        searchPurchase.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        mRefPurchase.orderByChild("idUser").equalTo(idUser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Purchase purchase = snapshot.getValue(Purchase.class);
                eventsMyPurchaseUserList.add(purchase);
                userMyPurchasesAdapter.notifyDataSetChanged();
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