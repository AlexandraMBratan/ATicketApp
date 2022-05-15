package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class AdminUserDetailsPageActivity extends AppCompatActivity {

    TextView numeUser,prenumeUser, varstaUser, telefonUser, codPostalUser, emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_details_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        numeUser = (TextView) findViewById(R.id.nume_userDetails_admin);
        prenumeUser = (TextView) findViewById(R.id.prenume_userDetails_admin);
        varstaUser = (TextView) findViewById(R.id.varsta_userDetails_admin);
        telefonUser = (TextView) findViewById(R.id.telefon_userDetails_admin);
        codPostalUser = (TextView) findViewById(R.id.codPostal_userDetails_admin);
        emailUser = (TextView) findViewById(R.id.email_userDetails_admin);

        String surname = getIntent().getStringExtra("nume");
        String name = getIntent().getStringExtra("prenume");
        String age = getIntent().getStringExtra("varsta");
        String telephone = getIntent().getStringExtra("telefon");
        String postalCode = getIntent().getStringExtra("codPostal");
        String email = getIntent().getStringExtra("email");

        numeUser.setText(surname);
        prenumeUser.setText(name);
        varstaUser.setText(age);
        telefonUser.setText(telephone);
        codPostalUser.setText(postalCode);
        emailUser.setText(email);
    }


}