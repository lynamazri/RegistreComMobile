package com.example.registrecommobile;

import android.content.Intent;
import android.content.SharedPreferences;
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

        DatabaseHandler db = new DatabaseHandler(this);


        if (isValidCredentials(username, password)) {
            User user = db.getUserByUsername(username);
            if(user != null && user.getPassword().equals(password)) {
            showToast("Login successful");
            saveUserSession(user.getID());
            Intent intent = new Intent(this, HomeActivity.class);
            //intent.putExtra("USERNAME_EXTRA", username);
            startActivity(intent);
            } else {
                showToast("Invalid credentials");
            }
        } else {
            showToast("Fields cannot be empty");
        }
    }

    private void saveUserSession(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("USER_ID", userId);
        editor.apply();
    }


    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        return sharedPreferences.contains("USER_ID");
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
