package com.comp2000.cwtest;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeMenu extends AppCompatActivity {
    private final String title = "MAIN MENU";
    Button button_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        button_edit = findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMenu.this, EditDetails.class);
                startActivity(intent);
            }
        });

        TextView textView_title = findViewById(R.id.title);
        textView_title.setText(title);

        // region sharedpreferences get user data and fill page
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        User_info user = new User_info(
                sharedPref.getString("name", "defaulted"),
                sharedPref.getString("address", "defaulted"),
                sharedPref.getString("phone_number", "defaulted"),
                sharedPref.getString("holiday_remaining", "defaulted")
        );

        TextView textView_name = findViewById(R.id.name);
        textView_name.setText(user.getName());

        TextView textView_address = findViewById(R.id.address);
        textView_address.setText(user.getAddress());

        TextView textView_phone_number = findViewById(R.id.phone_number);
        textView_phone_number.setText(user.getPhone_number());

        TextView textView_holiday_remaining = findViewById(R.id.number_holiday_remaining);
        textView_holiday_remaining.setText(user.getHoliday_remaining());
        // endregion sharedpreferences get user data and fill page

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:

                        return true;
                    case R.id.holiday:
                        Intent intent = new Intent(HomeMenu.this, HolidayMenu.class);
                        startActivity(intent);
                        return true;
                    case R.id.edit:
                        intent = new Intent(HomeMenu.this, EditDetails.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });



    }
}