package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityAdminHomePageBinding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AdminHomePageActivity extends AdminNavDrawerActivity {

    ActivityAdminHomePageBinding activityAdminHomePageBinding;
    FirebaseDatabase mDatabaseCat;
    DatabaseReference mRefCat;
    FirebaseStorage mStorageCat;
    RecyclerView recyclerViewCat;
    AdminCategoryAdapter adminCategoryAdapter;
    List<Category> categoryAdminList;
    //CardView cardConcertAdmin, cardFestivalAdmin,cardTeatruAdmin,cardSportAdmin;
    CardView cardCategory;
    Button addCategoryButton;
    private AdminCategoryAdapter.RecyclerViewInterfaceCategory recyclerViewInterfaceCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminHomePageBinding = ActivityAdminHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminHomePageBinding.getRoot());
        setActionBarTitle("Home");

        mDatabaseCat = FirebaseDatabase.getInstance();
        mRefCat = mDatabaseCat.getReference().child("Categorie");
        mStorageCat = FirebaseStorage.getInstance();

        recyclerViewCat = findViewById(R.id.categoryList_admin);
        recyclerViewCat.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AdminHomePageActivity.this,2);
        recyclerViewCat.setLayoutManager(gridLayoutManager);
        //recyclerViewCat.setLayoutManager(new LinearLayoutManager(this));

        categoryAdminList = new ArrayList<Category>();
        adminCategoryAdapter = new AdminCategoryAdapter(AdminHomePageActivity.this,categoryAdminList, recyclerViewInterfaceCategory);
        recyclerViewCat.setAdapter(adminCategoryAdapter);

        addCategoryButton = (Button) findViewById(R.id.addCategory);
        cardCategory = (CardView) findViewById(R.id.cardCategorieAdmin);

        //setOnClickListner();

        mRefCat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Category category = snapshot.getValue(Category.class);
                categoryAdminList.add(category);
                adminCategoryAdapter.notifyDataSetChanged();
                //setOnClickListner();
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


        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePageActivity.this,AdminAddCategoryPageActivity.class));
            }
        });
    }

    private void setOnClickListner() {
        recyclerViewInterfaceCategory = new AdminCategoryAdapter.RecyclerViewInterfaceCategory() {
            @Override
            public void onItemClick(View v, int position) {
                //Intent i = new Intent(getApplicationContext(), AdminEventsPageActivity.class);
                //i.putExtra("denumire", categoryAdminList.get(position).getDenumire());
                //startActivity(i);
                Toast.makeText(AdminHomePageActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        };
    }

}