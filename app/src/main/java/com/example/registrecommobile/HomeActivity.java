package com.example.registrecommobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);

        if (userId == -1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        DatabaseHandler db = new DatabaseHandler(this);
        User user = db.getUserById(userId);

        if (user != null) {
            String username = user.getUsername();

            TextView welcomeTextView = findViewById(R.id.textViewWelcome);
            welcomeTextView.setText("Hello, " + username);

            Button submitRequestButton = findViewById(R.id.buttonSubmitRequest);
            Button viewRequestStatusButton = findViewById(R.id.buttonViewRequestStatus);
            Button resourcesButton = findViewById(R.id.buttonResources);
            Button clientSupportButton = findViewById(R.id.buttonClientSupport);


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
        } else {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
    }
}
