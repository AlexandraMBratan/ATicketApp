package com.example.aticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    TextView data_selectata;
    Button adaugaData;
    CalendarView calendar;
    Calendar ziuaCurenta;
    SimpleDateFormat format;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        data_selectata = (TextView) findViewById(R.id.data_selectata);
        adaugaData = (Button) findViewById(R.id.dateButton);
        calendar = findViewById(R.id.calendar);

        ziuaCurenta = Calendar.getInstance();
        format = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setMinDate(ziuaCurenta.getTimeInMillis());
        data = format.format(calendar.getDate());
        data_selectata.setText(data);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                String s = i2 + "/" + (i1+1) + "/" + i;
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, i);
                c.set(Calendar.MONTH,i1);
                c.set(Calendar.DAY_OF_MONTH,i2);

                SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
                data = date_format.format(calendar.getDate());
                data_selectata.setText(s);
            }
        });

        adaugaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, AdminAddEventPageActivity.class);
                intent.putExtra("dataEveniment", data_selectata.getText());
                startActivity(intent);

            }
        });

    }
}