package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.aticketapp.databinding.ActivityAdminAddEventPageBinding;
import com.example.aticketapp.databinding.ActivityAdminHomePageBinding;
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

import java.util.ArrayList;
import java.util.List;

public class AdminAddEventPageActivity extends AdminNavDrawerActivity {

    ActivityAdminAddEventPageBinding activityAdminAddEventPageBinding;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef, mRefCategory;
    FirebaseStorage mStorage;
    ImageButton imageEventButton;
    EditText editNume, editArtist, editData, editOra, editLocatie, editPret, editCantitate;
    TextInputLayout editDescriere;
    Button butonAaugaEveniment;
    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;
    Spinner spinnerEventType;
    ArrayList<String> categoryList;
    ArrayAdapter<String> adapterSpiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminAddEventPageBinding =  ActivityAdminAddEventPageBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAddEventPageBinding.getRoot());
        setActionBarTitle("Add event");

        imageEventButton = (ImageButton) findViewById(R.id.imageEvent);

        editNume = (EditText) findViewById(R.id.numeEvent);
        editArtist = (EditText) findViewById(R.id.artistEvent);
        editData = (EditText) findViewById(R.id.dateEvent);
        editOra = (EditText) findViewById(R.id.timeEvent);
        editLocatie = (EditText) findViewById(R.id.locationEvent);
        editPret = (EditText) findViewById(R.id.priceEvent);
        editCantitate = (EditText) findViewById(R.id.totalQuantityEvent);
        editDescriere = (TextInputLayout) findViewById(R.id.descriereEveniment);
        spinnerEventType = (Spinner) findViewById(R.id.spinnerEventType);

        butonAaugaEveniment = (Button) findViewById(R.id.addEvent);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Evenimente");
        mStorage = FirebaseStorage.getInstance();
        mRefCategory = mDatabase.getReference().child("Categorie");

        categoryList = new ArrayList<String>();
        adapterSpiner = new ArrayAdapter<>(this, R.layout.spinner_category, categoryList);
        spinnerEventType.setAdapter(adapterSpiner);

        progressDialog = new ProgressDialog(this);

        imageEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });

        showSpinner();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageUrl=data.getData();
            imageEventButton.setImageURI(imageUrl);
        }

        butonAaugaEveniment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editNume.getText().toString().trim();
                String artist = editArtist.getText().toString().trim();
                String date = editData.getText().toString().trim();
                String time = editOra.getText().toString().trim();
                String location = editLocatie.getText().toString().trim();
                String price = editPret.getText().toString().trim();
                String quantity = editCantitate.getText().toString().trim();
                String type = spinnerEventType.getSelectedItem().toString();
                String description = editDescriere.getEditText().getText().toString().trim();


                if(!(name.isEmpty() && artist.isEmpty() && type.isEmpty() && date.isEmpty()
                        && time.isEmpty() && location.isEmpty() && price.isEmpty() && quantity.isEmpty() && imageUrl!=null)){
                    progressDialog.setTitle("Se incarca ...");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().
                                    addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();

                                    String id = mRef.push().getKey();
                                    Event newEvent = new Event(id,name,artist,type,date,time,location,price,quantity,description,t);

                                    mRef.child(id).setValue(newEvent);

                                    progressDialog.dismiss();

                                    Intent intent = new Intent(AdminAddEventPageActivity.this, AdminEventsListPageActivity.class);
                                    startActivity(intent);


                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void showSpinner(){
        mRefCategory.addValueEventListener(new ValueEventListener() {
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