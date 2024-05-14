package com.example.travel_app_srilanka.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_srilanka.R;

public class ActivitySignup extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signupButton, loginButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.editTextText2);
        emailEditText = findViewById(R.id.editTextText3);
        passwordEditText = findViewById(R.id.editTextText4);
        confirmPasswordEditText = findViewById(R.id.editTextTextPassword);
        signupButton = findViewById(R.id.button4);
        loginButton = findViewById(R.id.button3); // New login button reference

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitySignup.this, ActivityLogin.class));
            }
        });
    }

    private void signup() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();


        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        if (databaseHelper.checkUser(email, password)) {
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            return;
        }


        databaseHelper.addUser(name, email, password);

        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();


        startActivity(new Intent(ActivitySignup.this, ActivityLogin.class));
    }
}
