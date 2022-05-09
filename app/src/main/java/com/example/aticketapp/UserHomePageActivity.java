package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityUserHomePageBinding;

public class UserHomePageActivity extends UserNavDrawerActivity {

    ActivityUserHomePageBinding activityUserHomePageBinding;
    CardView cardConcertUser, cardFestivalUser,cardTeatruUser,cardSportUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserHomePageBinding = ActivityUserHomePageBinding.inflate(getLayoutInflater());
        setContentView(activityUserHomePageBinding.getRoot());
        setActionBarTitle("Home");

        cardConcertUser = (CardView) findViewById(R.id.cardConcerteUser);
        cardFestivalUser = (CardView) findViewById(R.id.cardFestivalUser);
        cardTeatruUser = (CardView) findViewById(R.id.cardTeatruUser);
        cardSportUser = (CardView) findViewById(R.id.cardSportUser);

        cardConcertUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserHomePageActivity.this, "Concerts Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,ConcertPageUser.class));
            }
        });

        cardFestivalUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserHomePageActivity.this, "Festivals Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,FestivalPageUser.class));
            }
        });

        cardTeatruUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserHomePageActivity.this, "Theatre Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,TheatrePageUser.class));
            }
        });

        cardSportUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserHomePageActivity.this, "Sports Page", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,SportPageUser.class));
            }
        });
    }

}