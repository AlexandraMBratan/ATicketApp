package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserPaymentPageActivity extends AppCompatActivity {

    String nameEvent, artistEvent, timeEvent, dateEvent, locationEvent, priceEvent, descriptionEvent, idEvent, imageEvent;
    String total, cantitateBilete;
    TextView sumaTotala;
    FirebaseDatabase mDatabasePurchase;
    DatabaseReference mRefPurchase;
    FirebaseUser user;
    Button payTickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        payTickets = (Button) findViewById(R.id.payTicketUser);
        sumaTotala = (TextView) findViewById(R.id.suma_totala_user) ;

        nameEvent = getIntent().getStringExtra("numeEveniment");
        artistEvent = getIntent().getStringExtra("artist");
        timeEvent = getIntent().getStringExtra("ora");
        dateEvent = getIntent().getStringExtra("data");
        locationEvent = getIntent().getStringExtra("locatie");
        priceEvent = getIntent().getStringExtra("pret");
        descriptionEvent = getIntent().getStringExtra("descriere");
        idEvent = getIntent().getStringExtra("idEvent");
        imageEvent = getIntent().getStringExtra("imagine");
        total = getIntent().getStringExtra("pretTotal");
        cantitateBilete = getIntent().getStringExtra("cantitateBilete");

        sumaTotala.setText(total);

        mDatabasePurchase = FirebaseDatabase.getInstance();
        mRefPurchase = mDatabasePurchase.getReference().child("Achizitii");
        user = FirebaseAuth.getInstance().getCurrentUser();

        payTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPurchase = mRefPurchase.push().getKey();
                String idUser = user.getUid();
                String status = "achizitie";

                Purchase newPuchase = new Purchase(idPurchase,idEvent,idUser,nameEvent,artistEvent,locationEvent,imageEvent,cantitateBilete,status,total);

                mRefPurchase.child(idPurchase).setValue(newPuchase);

                Intent intent = new Intent(UserPaymentPageActivity.this, UserHomePageActivity.class);
                startActivity(intent);
            }
        });

    }
}