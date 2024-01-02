package com.example.registrecommobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClick();
            }
        });
    }

    private void onSignUpClick() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (validateInput(username, password, confirmPassword)) {
            // Check if the username is already taken
            DatabaseHandler db = new DatabaseHandler(this);
            if (db.getUserByUsername(username) == null) {
                // Username is available, proceed with sign-up
                User newUser = new User(username, password);
                db.addUser(newUser);
                showToast("Sign up successful");

                // Navigate to HomeActivity
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("USERNAME_EXTRA", newUser.getUsername());
                startActivity(intent);
            } else {
                showToast("Username is already taken");
            }
        }
    }


    private boolean validateInput(String username, String password, String confirmPassword) {
        // Add your validation logic here
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showToast("Please fill in all fields");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showToast("Passwords do not match");
            return false;
        }

        // You can add more validation rules as needed

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
