package com.example.registrecommobile;

// SignUpActivity.java
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextNewUsername, editTextNewPassword;
    private Button buttonSignUp;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DatabaseHelper(this);
        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }

    private void signUpUser() {
        String newUsername = editTextNewUsername.getText().toString().trim();
        String newPassword = editTextNewPassword.getText().toString().trim();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, newUsername);
        values.put(DatabaseHelper.COLUMN_PASSWORD, newPassword);

        long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);

        if (newRowId != -1) {
            // Successful sign-up
            Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
            finish(); // Close the sign-up activity and go back to the login screen
        } else {
            // Error in sign-up
            Toast.makeText(this, "Error in sign up", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
