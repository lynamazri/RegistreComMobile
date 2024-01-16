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

        String requestNum = String.valueOf(R.string.request);
        String companyname = String.valueOf(R.string.companyname);
        String address = String.valueOf(R.string.address);
        String phonenumber = String.valueOf(R.string.phonenumber);
        String companyemail = String.valueOf(R.string.companyemail);
        String typeofactivity = String.valueOf(R.string.typeofactivity);
        String fullname = String.valueOf(R.string.fullname);
        String dateofbirth = String.valueOf(R.string.dateofbirth);
        String nationality = String.valueOf(R.string.nationality);
        String idnumber = String.valueOf(R.string.idnumber);
        String state = String.valueOf(R.string.state);


        textViewRequestId.setText("Request Status for Request N° " + request.getReqId());
        textViewCompanyName.setText("Company name: " + request.getCompanyName());
        textViewAddress.setText("Address: "  + request.getAddress());
        textViewPhoneNumber.setText("Phone number: "  + request.getPhoneNumber());
        textViewEmailAddress.setText("Company email: " + request.getEmailAddress());
        textViewActivityType.setText("Type of activity: " + request.getActivityType());
        textViewFullName.setText("Full name: " + request.getFullName());
        textViewDateOfBirth.setText("Date of birth of applicant: " + request.getDateOfBirth());
        textViewNationality.setText("Nationality of applicant: " + request.getNationality());
        textViewIdNumber.setText("ID number of applicant: " + request.getIdNumber());
        textViewState.setText("Request state: " + request.getState());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

