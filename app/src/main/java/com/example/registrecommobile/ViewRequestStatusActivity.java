package com.example.registrecommobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewRequestStatusActivity extends AppCompatActivity {

    private TextView textViewCompanyName, textViewAddress, textViewPhoneNumber,
            textViewEmailAddress, textViewActivityType, textViewFullName,
            textViewDateOfBirth, textViewNationality, textViewIdNumber,
            textViewState, textViewRequestId;

    private DatabaseHandler db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_status);


        textViewCompanyName = findViewById(R.id.textViewCompanyName);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        textViewEmailAddress = findViewById(R.id.textViewEmailAddress);
        textViewActivityType = findViewById(R.id.textViewActivityType);
        textViewFullName = findViewById(R.id.textViewFullName);
        textViewDateOfBirth = findViewById(R.id.textViewDateOfBirth);
        textViewNationality = findViewById(R.id.textViewNationality);
        textViewIdNumber = findViewById(R.id.textViewIdNumber);
        textViewState = findViewById(R.id.textViewState);
        textViewRequestId = findViewById(R.id.textViewRequestId);


        db = new DatabaseHandler(this);


        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);

        if (userId != -1) {

            Request userRequest = db.getRequestByUserId(userId);


            if (userRequest != null) {
                displayRequestDetails(userRequest);
            } else {
                showToast("No request found for the current user.");
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        } else {
            showToast("User session expired. Please log in again.");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void displayRequestDetails(Request request) {
        textViewRequestId.setText(R.string.requestid + " " + request.getReqId());
        textViewCompanyName.setText(R.string.companyname + ": "+ request.getCompanyName());
        textViewAddress.setText(R.string.address + ": " + request.getAddress());
        textViewPhoneNumber.setText(R.string.phonenumber + ": " + request.getPhoneNumber());
        textViewEmailAddress.setText(R.string.companyemail + ": " + request.getEmailAddress());
        textViewActivityType.setText(R.string.typeofactivity + ": " + request.getActivityType());
        textViewFullName.setText(R.string.fullname + ": " + request.getFullName());
        textViewDateOfBirth.setText(R.string.dateofbirth + ": " + request.getDateOfBirth());
        textViewNationality.setText(R.string.nationality + ": " + request.getNationality());
        textViewIdNumber.setText(R.string.idnumber + ": " + request.getIdNumber());
        textViewState.setText(R.string.state + ": " + request.getState());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

