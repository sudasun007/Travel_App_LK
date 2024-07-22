package com.example.travel_app_srilanka.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.travel_app_srilanka.R;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroupTheme;
    private RadioButton rbLight;
    private RadioButton rbDark;
    private Button btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroupTheme = findViewById(R.id.radioGroupTheme);
        rbLight = findViewById(R.id.rbLight);
        rbDark = findViewById(R.id.rbDark);
        btnApply = findViewById(R.id.btnApply);

        // Load saved theme preference
        SharedPreferences sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        int themePref = sharedPreferences.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
        if (themePref == AppCompatDelegate.MODE_NIGHT_NO) {
            rbLight.setChecked(true);
        } else if (themePref == AppCompatDelegate.MODE_NIGHT_YES) {
            rbDark.setChecked(true);
        }

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupTheme.getCheckedRadioButtonId();
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (selectedId == R.id.rbLight) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
                } else if (selectedId == R.id.rbDark) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_YES);
                }
                editor.apply();
            }
        });
    }
}
