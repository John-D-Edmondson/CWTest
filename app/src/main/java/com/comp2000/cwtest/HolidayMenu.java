package com.comp2000.cwtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class HolidayMenu extends AppCompatActivity {
    private final String title = "HOLIDAY";
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_menu);

        TextView textView_title = findViewById(R.id.title);
        textView_title.setText(title);

        Button button_view_holiday_requests = findViewById(R.id.button_view_holiday_requests);
        button_view_holiday_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HolidayMenu.this, HolidayRequests.class);
                startActivity(intent);
            }
        });

        Button button_clear = findViewById(R.id.button_clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date_from_view = findViewById(R.id.edittext_date_from);
                date_from_view.getText().clear();
                EditText date_to_view = findViewById(R.id.edittext_date_to);
                date_to_view.getText().clear();
            }
        });

        Button button_save_holiday_request = findViewById(R.id.button_save_holiday_request);
        button_save_holiday_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date_from_view = findViewById(R.id.edittext_date_from);
                String date_from = date_from_view.getText().toString();
                EditText date_to_view = findViewById(R.id.edittext_date_to);
                String date_to = date_to_view.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("holiday_index", "1");
                editor.putString("holiday_from", date_from);
                editor.putString("holiday_to", date_to);
                editor.commit();
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.coordinator), "Request Made", Snackbar.LENGTH_LONG);
                mySnackbar.show();
                date_from_view.getText().clear();
                date_to_view.getText().clear();

            }
        });
        // region datepicker for date from to date to
        EditText date_from = findViewById(R.id.edittext_date_from);
        date_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(HolidayMenu.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                date_from.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }

                        }, year, month, day);
                picker.show();
            }
        });

        EditText date_to = findViewById(R.id.edittext_date_to);
        date_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(HolidayMenu.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                date_to.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }

                        }, year, month, day);
                picker.show();
            }
        });
        // endregion datepicker for date from to date to

        // region bottom navigation logic
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.holiday);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(HolidayMenu.this, HomeMenu.class);
                        startActivity(intent);

                        return true;
                    case R.id.holiday:

                        return true;
                    case R.id.edit:
                        intent = new Intent(HolidayMenu.this, EditDetails.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        // endregion bottom navigation logic




    }
}