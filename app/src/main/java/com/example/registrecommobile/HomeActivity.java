package com.example.registrecommobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String username = getIntent().getStringExtra("USERNAME_EXTRA");

        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        welcomeTextView.setText("Hello, " + username);

        Button submitRequestButton = findViewById(R.id.buttonSubmitRequest);
        Button viewRequestStatusButton = findViewById(R.id.buttonViewRequestStatus);
        Button resourcesButton = findViewById(R.id.buttonResources);
        Button clientSupportButton = findViewById(R.id.buttonClientSupport);

        // Set onClickListeners for each button
        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SubmitRequestActivity.class));
            }
        });

        viewRequestStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewRequestStatusActivity.class));
            }
        });

        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ResourcesActivity.class));
            }
        });

        clientSupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ClientSupportActivity.class));
            }
        });

    }
}
