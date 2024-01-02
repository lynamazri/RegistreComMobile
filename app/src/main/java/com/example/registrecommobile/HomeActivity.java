package com.example.registrecommobile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the username from the Intent
        String username = getIntent().getStringExtra("USERNAME_EXTRA");

        // Display the welcome message
        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        welcomeTextView.setText("Hello, " + username);
    }
}
