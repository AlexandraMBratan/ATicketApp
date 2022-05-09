package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aticketapp.databinding.ActivityUserMyProfilePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserMyProfilePageActivity extends UserNavDrawerActivity {

    private FirebaseUser user;
    private DatabaseReference referenceData;

    private String userId;

    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserMyProfilePageBinding activityUserMyProfilePageBinding = ActivityUserMyProfilePageBinding.inflate(getLayoutInflater());;
        setContentView(activityUserMyProfilePageBinding.getRoot());

        user = FirebaseAuth.getInstance().getCurrentUser();
        referenceData = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        final TextView numeTextView = (TextView) findViewById(R.id.textNume);
        final TextView prenumeTextView = (TextView) findViewById(R.id.textPrenume);
        final TextView telefonTextView = (TextView) findViewById(R.id.textTelefon);
        final TextView varstaTextView = (TextView) findViewById(R.id.textVarsta);
        final TextView postalTextView = (TextView) findViewById(R.id.textCodPostal);
        final TextView emailTextView = (TextView) findViewById(R.id.textEmail);
        final TextView parolaTextView = (TextView) findViewById(R.id.textParola);

        updateButton = (Button) findViewById(R.id.updateButton);

        referenceData.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile !=null){
                    String nume = userProfile.nume;
                    String prenume = userProfile.prenume;
                    String telefon = userProfile.telefon;
                    String varsta = userProfile.varsta;
                    String postal = userProfile.codPostal;
                    String email = userProfile.email;
                    String parola = userProfile.parola;

                    numeTextView.setText(nume);
                    prenumeTextView.setText(prenume);
                    telefonTextView.setText(telefon);
                    varstaTextView.setText(varsta);
                    postalTextView.setText(postal);
                    emailTextView.setText(email);
                    parolaTextView.setText(parola);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserMyProfilePageActivity.this, "Eroare",Toast.LENGTH_LONG).show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMyProfilePageActivity.this,UserUpdateProfilePageActivity.class));
                Toast.makeText(UserMyProfilePageActivity.this, "Update profile" , Toast.LENGTH_LONG).show();
            }
        });
    }
}