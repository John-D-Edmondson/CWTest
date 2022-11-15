package com.comp2000.cwtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HolidayRequests extends AppCompatActivity {
    private final String title = "HOLIDAY REQUESTS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_requests);

        TextView textView_title = findViewById(R.id.title);
        textView_title.setText(title);

        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        EditText date_from_view = findViewById(R.id.edittext_date_from);
        date_from_view.setText(sharedPref.getString("holiday_from", "defaulted"));
        EditText date_to_view = findViewById(R.id.edittext_date_to);
        date_to_view.setText(sharedPref.getString("holiday_to", "defaulted"));

        // region bottom navigation logic
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.holiday);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(HolidayRequests.this, HomeMenu.class);
                        startActivity(intent);

                        return true;
                    case R.id.holiday:

                        return true;
                    case R.id.edit:
                        intent = new Intent(HolidayRequests.this, EditDetails.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        // endregion bottom navigation logic

    }
}