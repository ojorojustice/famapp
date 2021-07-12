package com.example.famappapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn = findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this, MainActivity.class));
            }
        });

        EditText username, email, password, confirmPassword;
        username = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);
        Button registerUser = findViewById(R.id.signup);

        registerUser.setOnClickListener(v -> {
            Toast t;
            String usernameText = username.getText().toString();
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String confirmPasswordText = confirmPassword.getText().toString();
            if (usernameText.trim().length() < 1 || emailText.trim().length() < 1
                    || passwordText.trim().length() < 1 ||
                    confirmPasswordText.trim().length() < 1) {
                t = Toast.makeText(this, "Please fill all fields before you proceed.", Toast.LENGTH_SHORT);
                t.show();
            } else if (!passwordText.equals(confirmPasswordText)) {
                t = Toast.makeText(this, "Password does not match.", Toast.LENGTH_SHORT);
                t.show();
            } else {
                DatabaseHelper dbh = new DatabaseHelper(registerActivity.this);
                boolean userCreated = dbh.registerUser(usernameText, passwordText, emailText);
                if (userCreated) {
                    t = Toast.makeText(this, "User Created successfully.", Toast.LENGTH_SHORT);
                    t.show();
                    Intent homeIntent = new Intent(registerActivity.this, Home.class);
                    //////Do storage here so that we can check on the Home activity for authenticated user
                    startActivity(homeIntent);
                    finish();

                }
            }
        });
    }
}