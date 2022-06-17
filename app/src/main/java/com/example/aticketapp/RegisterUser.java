package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText editTextSurname,editTextName, editTextAge,editTextPhone, editTextPostalCode, editTextEmail, editTextPassword;
    private TextView registerUser,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        login=(TextView) findViewById(R.id.login);
        login.setOnClickListener(this);

        editTextSurname = (EditText) findViewById(R.id.surname);
        editTextName = (EditText) findViewById(R.id.name);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextPhone = (EditText) findViewById(R.id.phone);
        editTextPostalCode = (EditText) findViewById(R.id.postalCode);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerUser:
                registerUser();
                break;
            case R.id.login:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void registerUser() {
        String surname = editTextSurname.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String postalCode = editTextPostalCode.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(surname.isEmpty()){
            editTextSurname.setError("Numele este necesar!");
            editTextSurname.requestFocus();
            return;
        }

        if(name.isEmpty()){
            editTextName.setError("Prenumele este necesar!");
            editTextName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextAge.setError("Varsta este necesara!");
            editTextAge.requestFocus();
            return;
        }

        if(phone.isEmpty()){
            editTextPhone.setError("Numarul de telefon este necesar!");
            editTextPhone.requestFocus();
            return;
        }

        if(postalCode.isEmpty()){
            editTextPostalCode.setError("Codul postal este necesar!");
            editTextPostalCode.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email este necesar!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Introduceti un email valid");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Parola este necesara!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() <6){
            editTextPassword.setError("Parola trebuie sa aiba minim 6 caractere!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(surname,name,age,phone,postalCode,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User-ul a fost inregistrat cu succes", Toast.LENGTH_LONG)
                                                .show();
                                    }else{
                                        Toast.makeText(RegisterUser.this, "Inregistrarea a esuat! Incercati din nou.", Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterUser.this, "Inregistrarea a esuat! Incercati din nou.", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }
}