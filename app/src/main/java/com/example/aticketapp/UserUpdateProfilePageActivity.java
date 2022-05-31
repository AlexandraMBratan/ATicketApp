package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.PhantomReference;

public class UserUpdateProfilePageActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout nume, prenume, varsta, telefon, codPostal, email, parola;
    private FirebaseUser user;
    private DatabaseReference referenceDatabase;

    private Button updateUser;
    String user_nume, user_prenume, user_varsta, user_telefon, user_codPostal, user_email, user_parola;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_profile_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        referenceDatabase = FirebaseDatabase.getInstance().getReference("Users");

        userID = user.getUid();

        nume = findViewById(R.id.updateNume);
        prenume = findViewById(R.id.updatePrenume);
        varsta = findViewById(R.id.updateVarsta);
        telefon = findViewById(R.id.updateTelefon);
        codPostal = findViewById(R.id.updateCodPostal);
        email = findViewById(R.id.updateEmail);
        parola = findViewById(R.id.updateParola);

        referenceDatabase.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    user_nume = userProfile.nume;
                    user_prenume = userProfile.prenume;
                    user_varsta = userProfile.varsta;
                    user_telefon = userProfile.telefon;
                    user_codPostal = userProfile.codPostal;
                    user_email = userProfile.email;
                    user_parola = userProfile.parola;

                    nume.getEditText().setText(user_nume);
                    prenume.getEditText().setText(user_prenume);
                    varsta.getEditText().setText(user_varsta);
                    telefon.getEditText().setText(user_telefon);
                    codPostal.getEditText().setText(user_codPostal);
                    email.getEditText().setText(user_email);
                    parola.getEditText().setText(user_parola);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserUpdateProfilePageActivity.this, "Eroare la actualizare!", Toast.LENGTH_LONG).show();
            }
        });

        updateUser = (Button) findViewById(R.id.updateUserButton);
        updateUser.setOnClickListener(this);
    }

    private void updateProfile() {
        if (isNumeChanged() || isPrenumeChanged() || isVarstaChanged() || isTelefonChanged() || isCodPostalChanged() || isEmailChanged() || isParolaChanged()) {
            Toast.makeText(this, "Informatiile au fost actualizate!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Informatile nu pot fi actualizate!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isParolaChanged() {
        if (!user_parola.equals(parola.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("parola").setValue(parola.getEditText().getText().toString());
            user_parola = parola.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }


    private boolean isEmailChanged() {
        if (!user_email.equals(email.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("email").setValue(email.getEditText().getText().toString());
            user_email = email.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isCodPostalChanged() {
        if (!user_codPostal.equals(codPostal.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("codPostal").setValue(codPostal.getEditText().getText().toString());
            user_codPostal = codPostal.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isTelefonChanged() {
        if (!user_telefon.equals(telefon.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("telefon").setValue(telefon.getEditText().getText().toString());
            user_telefon = telefon.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isVarstaChanged() {
        if (!user_varsta.equals(varsta.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("varsta").setValue(varsta.getEditText().getText().toString());
            user_varsta = varsta.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isPrenumeChanged() {
        if (!user_prenume.equals(prenume.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("prenume").setValue(prenume.getEditText().getText().toString());
            user_prenume = prenume.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isNumeChanged() {

        if (!user_nume.equals(nume.getEditText().getText().toString())) {
            referenceDatabase.child(userID).child("nume").setValue(nume.getEditText().getText().toString());
            user_nume = nume.getEditText().getText().toString();
            return true;

        } else {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.updateUserButton:
                updateProfile();
                break;
        }

        Intent intent = new Intent(UserUpdateProfilePageActivity.this, UserMyProfilePageActivity.class);
        startActivity(intent);
    }
}