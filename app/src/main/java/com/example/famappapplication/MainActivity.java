package com.example.famappapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Toast t;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView btn = findViewById(R.id.signUpBtn);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, registerActivity.class));
            finish();
        });

        //username and password validation and collection activity
        Button welcome = findViewById(R.id.login);
        welcome.setOnClickListener(v -> {
            username = (EditText) findViewById(R.id.inputUsername);
            password = (EditText) findViewById(R.id.inputPassword);
            if (username.getText().toString().trim().length() < 1) {
                t = Toast.makeText(this, "Username can not be empty", Toast.LENGTH_SHORT);
                t.show();
            } else if (password.getText().toString().trim().length() < 1) {
                t = Toast.makeText(this, "Password can not be empty", Toast.LENGTH_SHORT);
                t.show();
            } else {
                DatabaseHelper dbh = new DatabaseHelper(MainActivity.this);
                boolean loggedIn = dbh.login(username.getText().toString(), password.getText().toString());
                if (loggedIn) {
                    startActivity(new Intent(MainActivity.this, Home.class));
                    finish();
                } else {
                    t = Toast.makeText(this, "Invalid Username or Password, please check credentials or register.", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

    }
}