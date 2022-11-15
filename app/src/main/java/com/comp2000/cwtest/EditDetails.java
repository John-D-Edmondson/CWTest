package com.comp2000.cwtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class EditDetails extends AppCompatActivity {
    private final String title = "EDIT DETAILS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);

        TextView textView_title = findViewById(R.id.title);
        textView_title.setText(title);

        // region implements sharedPreferences
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
        // endregion implements sharedPreferences  f for dat

        Button button_cancel = findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDetails.this, HomeMenu.class);
                startActivity(intent);
            }
        });

        // region button_save logic
        Button button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.coordinator), "Details Saved", Snackbar.LENGTH_LONG);
                mySnackbar.show();

                EditText new_Street1 = findViewById(R.id.text_edit_street);
                String new_street1_string = new_Street1.getText().toString();

//                EditText new_Street2 = findViewById(R.id.text_edit_street);
//                String new_street2_string = new_Street2.getText().toString();
                EditText new_City = findViewById(R.id.text_edit_city);
                String new_City_string = new_City.getText().toString();

                EditText new_postcode = findViewById(R.id.text_edit_postcode);
                String new_postcode_string = new_postcode.getText().toString();

                String updated_address = new_street1_string + "\n"
                        + new_City_string + "\n" + new_postcode_string;

                EditText new_phoneNumber = findViewById(R.id.text_edit_phone_number);
                String new_phone_number_string = new_phoneNumber.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (!new_street1_string.isEmpty()) {
                    editor.putString("address", updated_address);
                    textView_address.setText(updated_address);
                    new_City.getText().clear();
                    new_Street1.getText().clear();
                    new_postcode.getText().clear();
                }
                if (!new_phone_number_string.isEmpty()) {
                    editor.putString("phone_number", new_phone_number_string);
                    textView_phone_number.setText(new_phone_number_string);
                    new_phoneNumber.getText().clear();
                }
                editor.commit();

            }
        });
        // endregion button_save logic

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.edit);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(EditDetails.this, HomeMenu.class);
                        startActivity(intent);

                        return true;
                    case R.id.holiday:
                        intent = new Intent(EditDetails.this, HolidayMenu.class);
                        startActivity(intent);
                        return true;
                    case R.id.edit:

                        return true;
                }
                return false;
            }
        });

    }


}