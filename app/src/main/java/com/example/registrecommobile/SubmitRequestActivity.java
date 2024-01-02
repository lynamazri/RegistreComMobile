package com.example.registrecommobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitRequestActivity extends AppCompatActivity {

    private EditText editTextCompanyName, editTextAddress, editTextActivityType, editTextPersonalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_request);

        editTextCompanyName = findViewById(R.id.editTextCompanyName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextActivityType = findViewById(R.id.editTextActivityType);
        editTextPersonalInfo = findViewById(R.id.editTextFullName);

        Button buttonSubmitRequest = findViewById(R.id.buttonSubmitRequest);
        buttonSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitRequest();
            }
        });
    }

    private void onSubmitRequest() {

        String companyName = editTextCompanyName.getText().toString();
        String address = editTextAddress.getText().toString();
        String activityType = editTextActivityType.getText().toString();
        String personalInfo = editTextPersonalInfo.getText().toString();


        if (companyName.isEmpty() || address.isEmpty() || activityType.isEmpty() || personalInfo.isEmpty()) {
            showToast("Please fill in all mandatory fields");
            return;
        }

        //add to db

        showToast("Request submitted successfully");

        //go back to home
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
