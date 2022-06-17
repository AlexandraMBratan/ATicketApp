package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserEventDetailsPageActivity extends AppCompatActivity {

    TextView numeEveniment, artist, ora, data, locatie, pret, descriere;
    String nameEvent, artistEvent, timeEvent, dateEvent, locationEvent, priceEvent, descriptionEvent, idEvent, imageEvent;
    ImageView imagineEveniment;
    Button reserveTickets, buyTickets;
    ImageButton minusButton, plusButton;
    TextView cantitateBilete;
    FirebaseDatabase mDatabase;
    DatabaseReference mRefEvent;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_details_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        numeEveniment = (TextView) findViewById(R.id.numeEveniment_eventDetails_user);
        artist = (TextView) findViewById(R.id.artist_eventDetails_user);
        ora = (TextView) findViewById(R.id.ora_eventDetails_user);
        data =  (TextView) findViewById(R.id.data_eventDetails_user);
        locatie = (TextView) findViewById(R.id.locatie_eventDetails_user);
        pret = (TextView) findViewById(R.id.pret_eventDetails_user);
        descriere = (TextView) findViewById(R.id.descriere_eventDetails_user);
        imagineEveniment = (ImageView) findViewById(R.id.image_eventDetails_user);
        reserveTickets = (Button) findViewById(R.id.reserveEventButton);
        buyTickets = (Button) findViewById(R.id.buyEventButton);
        plusButton = (ImageButton) findViewById(R.id.plus_cantitate);
        minusButton = (ImageButton) findViewById(R.id.minus_cantitate);
        cantitateBilete = (TextView) findViewById(R.id.cantitate_bilete);

        nameEvent = getIntent().getStringExtra("numeEveniment");
        artistEvent = getIntent().getStringExtra("artist");
        timeEvent = getIntent().getStringExtra("ora");
        dateEvent = getIntent().getStringExtra("data");
        locationEvent = getIntent().getStringExtra("locatie");
        priceEvent = getIntent().getStringExtra("pret");
        descriptionEvent = getIntent().getStringExtra("descriere");
        idEvent = getIntent().getStringExtra("idEvent");
        imageEvent = getIntent().getStringExtra("imagine");

        numeEveniment.setText(nameEvent);
        artist.setText(artistEvent);
        ora.setText(timeEvent);
        data.setText(dateEvent);
        locatie.setText(locationEvent);
        pret.setText(priceEvent);
        descriere.setText(descriptionEvent);
        Picasso.get().load(imageEvent).into(imagineEveniment);

        mDatabase = FirebaseDatabase.getInstance();
        mRefEvent = mDatabase.getReference().child("Rezervari");
        user = FirebaseAuth.getInstance().getCurrentUser();

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valoareCurenta = cantitateBilete.getText().toString();
                int valoare = Integer.parseInt(valoareCurenta);
                valoare++;
                cantitateBilete.setText(String.valueOf(valoare));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valoareCurenta = cantitateBilete.getText().toString();
                int valoare = Integer.parseInt(valoareCurenta);
                if(valoare>0){
                    valoare--;
                    cantitateBilete.setText(String.valueOf(valoare));
                }else{
                    Toast.makeText(UserEventDetailsPageActivity.this, "Numarul biletelor trebuie sa fie pozitiv!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reserveTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idReservation = mRefEvent.push().getKey();
                String idUser = user.getUid();
                String cantitate = cantitateBilete.getText().toString();
                String status = "rezervare";

                String pret = priceEvent.replaceAll("[\\D]", "");
                int pretNumar = Integer.valueOf(pret);

                int cantitateNumar = Integer.parseInt(cantitate);

                int total = pretNumar * cantitateNumar;

                String pretTotal = total + " lei";

                Reservation newReservation = new Reservation(idReservation,idEvent,idUser, nameEvent, artistEvent, locationEvent, imageEvent, cantitate,status,pretTotal);

                mRefEvent.child(idReservation).setValue(newReservation);

                Intent intent = new Intent(UserEventDetailsPageActivity.this, UserHomePageActivity.class);
                startActivity(intent);
            }
        });

        buyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String idReservation = mRefEvent.push().getKey();
                //String idUser = user.getUid();
                String cantitate = cantitateBilete.getText().toString();

                String pret = priceEvent.replaceAll("[\\D]", "");
                int pretNumar = Integer.valueOf(pret);

                int cantitateNumar = Integer.parseInt(cantitate);

                int total = pretNumar * cantitateNumar;

                String pretTotal = total + " lei";
                Intent intent = new Intent(UserEventDetailsPageActivity.this, UserPaymentPageActivity.class);
                intent.putExtra("numeEveniment", nameEvent);
                intent.putExtra("artist", artistEvent);
                intent.putExtra("data", dateEvent);
                intent.putExtra("ora", timeEvent);
                intent.putExtra("locatie", locationEvent);
                intent.putExtra("pret", priceEvent);
                intent.putExtra("descriere", descriptionEvent);
                intent.putExtra("idEvent", idEvent);
                intent.putExtra("imagine",imageEvent);
                intent.putExtra("pretTotal", pretTotal);
                intent.putExtra("cantitateBilete", cantitate);
                startActivity(intent);
                Toast.makeText(UserEventDetailsPageActivity.this, "Se vor cumpara bilete", Toast.LENGTH_SHORT).show();
            }
        });
    }

}