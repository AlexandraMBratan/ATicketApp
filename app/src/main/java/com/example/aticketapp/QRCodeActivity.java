package com.example.aticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class QRCodeActivity extends AppCompatActivity {
    ImageView qrCode;
    Button generare;
    String numeEveniment;
    //Bitmap bitmap;
    //QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_main)));


        qrCode = findViewById(R.id.qr_code);
        generare = findViewById(R.id.generareCod);

        numeEveniment = getIntent().getStringExtra("numeEveniment");

        generare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode(numeEveniment, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    qrCode.setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}