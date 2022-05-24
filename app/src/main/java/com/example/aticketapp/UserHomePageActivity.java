package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityUserHomePageBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class UserHomePageActivity extends UserNavDrawerActivity {

    ActivityUserHomePageBinding activityUserHomePageBinding;
    FirebaseDatabase mDatabaseCatUser;
    DatabaseReference mRefCatUser;
    FirebaseStorage mStorageCatUser;
    RecyclerView recyclerViewCatUser;
    UserCategoryAdapter userCategoryAdapter;
    List<Category> categoryUserList;
    //CardView cardConcertAdmin, cardFestivalAdmin,cardTeatruAdmin,cardSportAdmin;
    CardView cardCategory;
    private UserCategoryAdapter.RecyclerViewInterfaceCategory recyclerViewInterfaceCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserHomePageBinding = ActivityUserHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityUserHomePageBinding.getRoot());
        setActionBarTitle("Home");

        mDatabaseCatUser = FirebaseDatabase.getInstance();
        mRefCatUser = mDatabaseCatUser.getReference().child("Categorie");
        mStorageCatUser = FirebaseStorage.getInstance();

        recyclerViewCatUser = findViewById(R.id.categoryList_user);
        recyclerViewCatUser.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserHomePageActivity.this,2);
        recyclerViewCatUser.setLayoutManager(gridLayoutManager);

        categoryUserList = new ArrayList<Category>();
        userCategoryAdapter = new UserCategoryAdapter(UserHomePageActivity.this,categoryUserList, recyclerViewInterfaceCategory);
        recyclerViewCatUser.setAdapter(userCategoryAdapter);

        cardCategory = (CardView) findViewById(R.id.cardCategorieAdmin);

        mRefCatUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Category category = snapshot.getValue(Category.class);
                categoryUserList.add(category);
                userCategoryAdapter.notifyDataSetChanged();
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

    private void setOnClickListner() {
        recyclerViewInterfaceCategory = new UserCategoryAdapter.RecyclerViewInterfaceCategory() {
            @Override
            public void onItemClick(View v, int position) {
                //Intent i = new Intent(getApplicationContext(), AdminEventsPageActivity.class);
                //i.putExtra("denumire", categoryAdminList.get(position).getDenumire());
                //startActivity(i);
                Toast.makeText(UserHomePageActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        };
    }

}