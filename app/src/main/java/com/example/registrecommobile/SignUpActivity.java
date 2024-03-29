package com.example.registrecommobile;

import android.content.Intent;
import android.content.SharedPreferences;
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
        Button loginButton = findViewById(R.id.buttonLogin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClick();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });
    }

    private void onSignUpClick() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (validateInput(username, password, confirmPassword)) {

            if (!isValidPassword(password)) {
                showToast("Password must be between 8 and 16 characters and contain at least one number");
                return;
            }

            DatabaseHandler db = new DatabaseHandler(this);
            if (db.getUserByUsername(username) == null) {
                User newUser = new User(username, password);
                db.addUser(newUser);
                saveUserSession(newUser.getID());
                showToast("Sign up successful");

                Intent intent = new Intent(this, HomeActivity.class);
                //intent.putExtra("USERNAME_EXTRA", username);
                startActivity(intent);
            } else {
                showToast("Username is already taken");
            }
        }
    }
    private void saveUserSession(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("USER_ID", userId);
        editor.apply();
    }

    private boolean isValidPassword(String password) {

        return password.length() >= 8 && password.length() <= 16 && containsNumber(password);
    }

    private boolean containsNumber(String str) {

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateInput(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showToast("Please fill in all fields");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showToast("Passwords do not match");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
