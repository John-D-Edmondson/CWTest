package com.comp2000.cwtest;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button buttonClear;
    Button buttonLogin;
    public EditText username;
    public EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.edittext_username);
        password = (EditText) findViewById(R.id.edittext_password);
        buttonClear = findViewById(R.id.button_clear);
        buttonLogin = findViewById(R.id.button_login);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", "Keith2 Richards");
        editor.putString("address", "Test Streety Street \nPlymouth \nPL4 5AA ");
        editor.putString("phone_number", "01234567890");
        editor.putString("holiday_remaining", "999");
        editor.apply();






        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDetails();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeMenu.class);
                startActivity(intent);

            }
        });
    }


    public void clearDetails() {
        username.getText().clear();
        password.getText().clear();

    }
}