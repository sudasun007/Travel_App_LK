package com.example.travel_app_srilanka.Activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app_srilanka.R;

public class EditPersonalInfoActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonUpdate, buttonDelete;
    private DatabaseHelper databaseHelper;
    private String loggedInUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        databaseHelper = new DatabaseHelper(this);
        loggedInUserEmail = getCurrentUserEmail();

        // Load user info
        loadUserInfo();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePersonalInfo();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeletePersonalInfo();
            }
        });
    }

    private void loadUserInfo() {
        User user = databaseHelper.getUser(loggedInUserEmail);
        if (user != null) {
            editTextName.setText(user.getName());
            editTextEmail.setText(user.getEmail());
            editTextPassword.setText(""); // Hide password
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePersonalInfo() {
        String newName = editTextName.getText().toString();
        String newPassword = editTextPassword.getText().toString();

        boolean success = databaseHelper.updateUserInfo(loggedInUserEmail, newName, newPassword);
        if (success) {
            Toast.makeText(EditPersonalInfoActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EditPersonalInfoActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmDeletePersonalInfo() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deletePersonalInfo();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deletePersonalInfo() {
        boolean success = databaseHelper.deleteUser(loggedInUserEmail);
        if (success) {
            Toast.makeText(EditPersonalInfoActivity.this, "Delete successful", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
        } else {
            Toast.makeText(EditPersonalInfoActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentUserEmail() {
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        return preferences.getString("loggedInUserEmail", "default@example.com");
    }
}
