package com.example.registrecommobile;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitRequestActivity extends AppCompatActivity {

    private EditText editTextCompanyName, editTextAddress, editTextPhoneNumber,
            editTextEmailAddress, editTextActivityType, editTextFullName,
            editTextDateOfBirth, editTextNationality, editTextIdNumber;

    private CheckBox checkBoxDeclaration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_request);

        editTextCompanyName = findViewById(R.id.editTextCompanyName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextActivityType = findViewById(R.id.editTextActivityType);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        editTextNationality = findViewById(R.id.editTextNationality);
        editTextIdNumber = findViewById(R.id.editTextIdNumber);

        checkBoxDeclaration = findViewById(R.id.checkBoxDeclaration);

        Button buttonSubmitRequest = findViewById(R.id.buttonSubmitRequest);
        Button buttonCancelRequest = findViewById(R.id.buttonCancelRequest);

        buttonCancelRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubmitRequestActivity.this, HomeActivity.class));
            }
        });

        buttonSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitRequest();
            }
        });
    }

    private void onSubmitRequest() {


        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);

        if (userId == -1) {
            showToast("User session expired. Please log in again.");
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }


        String companyName = editTextCompanyName.getText().toString();
        String address = editTextAddress.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        String emailAddress = editTextEmailAddress.getText().toString();
        String activityType = editTextActivityType.getText().toString();
        String fullName = editTextFullName.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String nationality = editTextNationality.getText().toString();
        String idNumber = editTextIdNumber.getText().toString();
        boolean declarationChecked = checkBoxDeclaration.isChecked();


        if (companyName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() ||
                emailAddress.isEmpty() || activityType.isEmpty() || fullName.isEmpty() ||
                dateOfBirth.isEmpty() || nationality.isEmpty() || idNumber.isEmpty() ||
                !declarationChecked) {
            showToast("Please fill in all mandatory fields and accept the declaration.");
            return;
        }
        DatabaseHandler db = new DatabaseHandler(this);
        Request existingRequest = db.getRequestByUserId(userId);
        if (existingRequest != null) {
            showToast("Cannot submit another request. You already have a pending request.");
            return;
        }

        Request newRequest = new Request(companyName, address, phoneNumber, emailAddress,
                activityType, fullName, dateOfBirth, nationality, idNumber, userId, "Submitted");


        db.addRequest(newRequest);

        showToast("Request submitted successfully");
        finish();
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
