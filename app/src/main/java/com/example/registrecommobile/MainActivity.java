package com.example.registrecommobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClick();
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClick();
            }
        });
    }

    private void onLoginClick() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // Fetch user from the database based on the entered username
        DatabaseHandler db = new DatabaseHandler(this);
        User user = db.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            showToast("Login successful");

            // Navigate to HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close the current activity to prevent going back to the login screen
        } else {
            showToast("Invalid credentials");
        }
    }


    private void onSignUpClick() {

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    private boolean isValidCredentials(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
