package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityAdminHomePageBinding;

import java.util.ArrayList;
import java.util.List;

public class AdminHomePageActivity extends AdminNavDrawerActivity {

    ActivityAdminHomePageBinding activityAdminHomePageBinding;
    CardView cardConcertAdmin, cardFestivalAdmin,cardTeatruAdmin,cardSportAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminHomePageBinding =  ActivityAdminHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminHomePageBinding.getRoot());
        setActionBarTitle("Home");

        cardConcertAdmin = (CardView) findViewById(R.id.cardConcerteAdmin);
        cardFestivalAdmin = (CardView) findViewById(R.id.cardFestivalAdmin);
        cardTeatruAdmin = (CardView) findViewById(R.id.cardTeatruAdmin);
        cardSportAdmin = (CardView) findViewById(R.id.cardSportAdmin);

        cardConcertAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminHomePageActivity.this, "Concerts Page", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AdminHomePageActivity.this,AdminConcertsListPageActivity.class));
            }
        });

        cardFestivalAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminHomePageActivity.this, "Festivals Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,FestivalPageAdmin.class));
            }
        });

        cardTeatruAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminHomePageActivity.this, "Theatre Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,TheatrePageAdmin.class));
            }
        });

        cardSportAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminHomePageActivity.this, "Sports Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,SportPageAdmin.class));
            }
        });

    }
}