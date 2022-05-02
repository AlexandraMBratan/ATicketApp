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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword;
    private TextView forgotPassword,register;
    private Button signIn;
    
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        
        signIn = (Button) findViewById(R.id.signin);
        signIn.setOnClickListener(this);
        
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin:
                userLogin();
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        
        if(email.isEmpty()){
            editTextEmail.setError("Email este necesar!");
            editTextEmail.requestFocus();
            return;
        }
        
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Introduceti un email valid!");
            editTextEmail.requestFocus();
            return;
        }
        
        if(password.isEmpty()){
            editTextPassword.setError("Parola este necesara!");
            editTextPassword.requestFocus();
            return;
        }
        
        if(password.length()<6){
            editTextPassword.setError("Parola trebuie sa aiba minim 6 caractere!");
            editTextPassword.requestFocus();
            return;
        }
        
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(email.equals("admin@yahoo.com")){
                        Toast.makeText(MainActivity.this, "Admin sign in", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"User sign in", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Eroare la autentificare!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}