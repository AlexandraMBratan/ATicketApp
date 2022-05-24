package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminUpdateEventPageActivity extends AppCompatActivity {

    EditText denumire, artist, data, ora, locatie, pret, cantitateTotala;
    TextInputLayout descriere;
    ImageButton butonImagine;
    String idEvent;
    Spinner tip;
    ArrayList<String> categoryList;
    ArrayAdapter<String> adapterSpiner;
    FirebaseDatabase mDatabaseEvent;
    DatabaseReference mRefEvent, mRefCategoryEvent;
    FirebaseStorage mStorageEvent;
    private static final int Gallery_Code=1;
    Uri imageEventUrl=null;
    Button butonActualizareEveniment;
    String nameEvent, artistEvent, dateEvent, timeEvent, locationEvent, priceEvent, quantityEvent, descriptionEvent, typeEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_event_page);

        denumire = (EditText) findViewById(R.id.update_numeEvent);
        artist = (EditText) findViewById(R.id.update_artistEvent);
        data = (EditText) findViewById(R.id.update_dateEvent);
        ora = (EditText) findViewById(R.id.update_timeEvent);
        locatie = (EditText) findViewById(R.id.update_locationEvent);
        pret = (EditText) findViewById(R.id.update_priceEvent);
        cantitateTotala = (EditText) findViewById(R.id.update_totalQuantityEvent);
        descriere = (TextInputLayout) findViewById(R.id.update_descriereEveniment);
        tip = (Spinner) findViewById(R.id.update_spinnerEventType);
        butonImagine = (ImageButton) findViewById(R.id.update_imageEvent);
        butonActualizareEveniment = (Button) findViewById(R.id.updateEventButton);

        mDatabaseEvent = FirebaseDatabase.getInstance();
        mRefEvent = mDatabaseEvent.getReference().child("Evenimente");
        mStorageEvent = FirebaseStorage.getInstance();
        mRefCategoryEvent = mDatabaseEvent.getReference().child("Categorie");

        categoryList = new ArrayList<String>();
        adapterSpiner = new ArrayAdapter<>(this, R.layout.spinner_category, categoryList);
        tip.setAdapter(adapterSpiner);

        nameEvent = getIntent().getStringExtra("numeEveniment");
        artistEvent = getIntent().getStringExtra("artist");
        dateEvent = getIntent().getStringExtra("data");
        timeEvent = getIntent().getStringExtra("ora");
        locationEvent = getIntent().getStringExtra("locatie");
        priceEvent = getIntent().getStringExtra("pret");
        quantityEvent = getIntent().getStringExtra("cantitateTotala");
        descriptionEvent = getIntent().getStringExtra("descriere");
        typeEvent = getIntent().getStringExtra("tip");
        String imageEvent = getIntent().getStringExtra("imagine");
        idEvent = getIntent().getStringExtra("idEvent");

        denumire.setText(nameEvent);
        artist.setText(artistEvent);
        data.setText(dateEvent);
        ora.setText(timeEvent);
        locatie.setText(locationEvent);
        pret.setText(priceEvent);
        cantitateTotala.setText(quantityEvent);
        descriere.getEditText().setText(descriptionEvent);

        tip.setSelection(getIndex(tip,typeEvent));
        showSpinner();
        Picasso.get().load(imageEvent).into(butonImagine);
        // Tipul evenimentului sa fie pus cum trebuie, nu sa fie spinner-ul de la inceput
        // Imaginea sa poata fii schimbata

        butonImagine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });

        butonActualizareEveniment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.updateEventButton){
                    updateEvent();
                }
            }
        });
    }

    private void updateEvent() {
        if(isNameChanged() || isArtistChanged() || isDateChanged() || isTimeChanged() || isLocationChanged() || isPriceChanged()
        || isTotalQuantityChanged() || isDescriptionChanged() || isTypeChanged()){
            Toast.makeText(this, "Evenimentul a fost actualizat!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Evenimentul nu s-a actualizat, datele sunt la fel!", Toast.LENGTH_LONG).show();

        }
    }

    private boolean isTypeChanged() {
        if(!typeEvent.equals(tip.getSelectedItem().toString())){
            mRefEvent.child(idEvent).child("tip").setValue(tip.getSelectedItem().toString());
            typeEvent = tip.getSelectedItem().toString();
            return true;

        }else{
            return false;
        }
    }

    //MAI DE SCHIMBAT IMAGINEA

    private boolean isDescriptionChanged() {
        if(!descriptionEvent.equals(descriere.getEditText().getText().toString())){
            mRefEvent.child(idEvent).child("descriere").setValue(descriere.getEditText().getText().toString());
            descriptionEvent = descriere.getEditText().getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isTotalQuantityChanged() {
        if(!quantityEvent.equals(cantitateTotala.getText().toString())){
            mRefEvent.child(idEvent).child("cantitateTotala").setValue(cantitateTotala.getText().toString());
            quantityEvent = cantitateTotala.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isPriceChanged() {
        if(!priceEvent.equals(pret.getText().toString())){
            mRefEvent.child(idEvent).child("pret").setValue(pret.getText().toString());
            priceEvent = pret.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isLocationChanged() {
        if(!locationEvent.equals(locatie.getText().toString())){
            mRefEvent.child(idEvent).child("locatie").setValue(locatie.getText().toString());
            locationEvent = locatie.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isTimeChanged() {
        if(!timeEvent.equals(ora.getText().toString())){
            mRefEvent.child(idEvent).child("ora").setValue(ora.getText().toString());
            timeEvent = ora.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isDateChanged() {
        if(!dateEvent.equals(data.getText().toString())){
            mRefEvent.child(idEvent).child("data").setValue(data.getText().toString());
            dateEvent = data.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isArtistChanged() {
        if(!artistEvent.equals(artist.getText().toString())){
            mRefEvent.child(idEvent).child("artist").setValue(artist.getText().toString());
            artistEvent = artist.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!nameEvent.equals(denumire.getText().toString())){
            mRefEvent.child(idEvent).child("numeEveniment").setValue(denumire.getText().toString());
            nameEvent = denumire.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageEventUrl=data.getData();
            butonImagine.setImageURI(imageEventUrl);
        }
    }


    private int getIndex(Spinner tip, String typeEvent) {
        for(int i=0 ; i<tip.getCount(); i++){
            if(tip.getItemAtPosition(i).toString().equalsIgnoreCase(typeEvent)){
                return i;
            }
        }
        return 0;
    }

    private void showSpinner(){
        mRefCategoryEvent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot i : snapshot.getChildren()){
                    categoryList.add(i.child("denumire").getValue(String.class));
                }
                adapterSpiner.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}