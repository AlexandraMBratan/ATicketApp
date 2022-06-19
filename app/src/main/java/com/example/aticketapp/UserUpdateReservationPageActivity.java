package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class UserUpdateReservationPageActivity extends AppCompatActivity {

    TextView numeEveniment, artist, ora, data, locatie, pret, descriere;
    String nameEvent, artistEvent, timeEvent, dateEvent, locationEvent, priceEvent, descriptionEvent, idEvent, imageEvent, cantitateRezervare, idReservation,idUser,status;
    ImageView imagineEveniment;
    Button updateTickets, buyTickets;
    ImageButton minusButton, plusButton;
    TextView cantitateBilete;
    FirebaseDatabase mDatabase;
    DatabaseReference mRefEvent;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_reservation_page);

        numeEveniment = (TextView) findViewById(R.id.numeEveniment_reservationDetails_user);
        artist = (TextView) findViewById(R.id.artist_reservationDetails_user);
        ora = (TextView) findViewById(R.id.ora_reservationDetails_user);
        data =  (TextView) findViewById(R.id.data_reservationDetails_user);
        locatie = (TextView) findViewById(R.id.locatie_reservationDetails_user);
        pret = (TextView) findViewById(R.id.pret_reservationDetails_user);
        descriere = (TextView) findViewById(R.id.descriere_reservationDetails_user);
        imagineEveniment = (ImageView) findViewById(R.id.image_reservationDetails_user);
        updateTickets = (Button) findViewById(R.id.updateReservationButton);
        buyTickets = (Button) findViewById(R.id.buyReservationButton);
        plusButton = (ImageButton) findViewById(R.id.plus_cantitate_rezervare);
        minusButton = (ImageButton) findViewById(R.id.minus_cantitate_rezervare);
        cantitateBilete = (TextView) findViewById(R.id.cantitate_bilete_rezervare);

        nameEvent = getIntent().getStringExtra("numeEveniment");
        artistEvent = getIntent().getStringExtra("artist");
        timeEvent = getIntent().getStringExtra("ora");
        dateEvent = getIntent().getStringExtra("data");
        locationEvent = getIntent().getStringExtra("locatie");
        priceEvent = getIntent().getStringExtra("pret");
        descriptionEvent = getIntent().getStringExtra("descriere");
        idEvent = getIntent().getStringExtra("idEvent");
        imageEvent = getIntent().getStringExtra("imagine");
        cantitateRezervare = getIntent().getStringExtra("cantitateBileteRezervat");
        idReservation = getIntent().getStringExtra("idReservation");
        idUser = getIntent().getStringExtra("idUser");
        status = getIntent().getStringExtra("status");

        numeEveniment.setText(nameEvent);
        artist.setText(artistEvent);
        ora.setText(timeEvent);
        data.setText(dateEvent);
        locatie.setText(locationEvent);
        pret.setText(priceEvent);
        descriere.setText(descriptionEvent);
        Picasso.get().load(imageEvent).into(imagineEveniment);
        cantitateBilete.setText(cantitateRezervare);


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
                    Toast.makeText(UserUpdateReservationPageActivity.this, "Numarul biletelor trebuie sa fie pozitiv!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String idReservation = mRefEvent.push().getKey();
                DatabaseReference refData = FirebaseDatabase.getInstance().getReference().child("Rezervari").child(idReservation);
                String idUser = user.getUid();
                String cantitate = cantitateBilete.getText().toString();
                String status = "rezervare";

                String pret = priceEvent.replaceAll("[\\D]", "");
                int pretNumar = Integer.valueOf(pret);

                int cantitateNumar = Integer.parseInt(cantitate);

                int total = pretNumar * cantitateNumar;

                String pretTotal = total + " lei";


                //mRefEvent.child(idReservation).setValue(newReservation);

                refData.child("cantitateBileteRezervat").setValue(cantitate);

                //Reservation newReservation = new Reservation(idReservation,idEvent,idUser, nameEvent, artistEvent, locationEvent, imageEvent, cantitate,status,pretTotal, descriptionEvent,timeEvent,dateEvent,priceEvent);

                Intent intent = new Intent(UserUpdateReservationPageActivity.this, UserMyReservationsPageActivity.class);
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
                Intent intent = new Intent(UserUpdateReservationPageActivity.this, UserPaymentPageActivity.class);
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
                Toast.makeText(UserUpdateReservationPageActivity.this, "Se vor cumpara bilete", Toast.LENGTH_SHORT).show();

                Reservation newReservation = new Reservation(idReservation,idEvent,idUser, nameEvent, artistEvent, locationEvent, imageEvent, cantitate,status,pretTotal, descriptionEvent,timeEvent,dateEvent,priceEvent);

                DatabaseReference refData = FirebaseDatabase.getInstance().getReference().child("Rezervari").child(newReservation.getIdRezervation());
                FirebaseStorage mStorage = FirebaseStorage.getInstance();

                refData.removeValue();

            }
        });

    }
}