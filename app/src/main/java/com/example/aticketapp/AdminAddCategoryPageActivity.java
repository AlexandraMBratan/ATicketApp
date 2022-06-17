package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminAddCategoryPageActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabaseCategory;
    private DatabaseReference mRefCategory;
    private FirebaseStorage mStorageCategory;
    private ImageButton imageCategoryButton;
    private EditText textCategorie;
    private Button butonAdaugaCategorie;
    private static final int Gallery_Code=1;
    private Uri imageCategoryUrl=null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category_page);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));

        imageCategoryButton = (ImageButton) findViewById(R.id.imageCategorie);
        textCategorie = (EditText) findViewById(R.id.numeCategorie);
        butonAdaugaCategorie = (Button) findViewById(R.id.adaugaCategorie);

        mDatabaseCategory = FirebaseDatabase.getInstance();
        mRefCategory = mDatabaseCategory.getReference().child("Categorie");
        mStorageCategory = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        imageCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageCategoryUrl=data.getData();
            imageCategoryButton.setImageURI(imageCategoryUrl);
        }

        butonAdaugaCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String denumire = textCategorie.getText().toString().trim();
                if(!(denumire.isEmpty() && imageCategoryUrl!=null)){
                    progressDialog.setTitle("Se incarca ...");
                    progressDialog.show();
                    StorageReference filepath = mStorageCategory.getReference().child("imagePost").child(imageCategoryUrl.getLastPathSegment());
                    filepath.putFile(imageCategoryUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    String idCategorie = mRefCategory.push().getKey();
                                    Category newCategory = new Category(idCategorie,denumire,task.getResult().toString());
                                    mRefCategory.child(idCategorie).setValue(newCategory);
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(AdminAddCategoryPageActivity.this, AdminHomePageActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}