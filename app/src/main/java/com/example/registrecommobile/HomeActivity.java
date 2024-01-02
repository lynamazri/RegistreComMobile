package com.example.registrecommobile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String username = getIntent().getStringExtra("USERNAME_EXTRA");

        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        welcomeTextView.setText("Hello, " + username);
    }
}
