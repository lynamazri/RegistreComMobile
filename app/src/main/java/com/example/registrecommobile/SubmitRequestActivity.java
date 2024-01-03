package com.example.registrecommobile;
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

        // TODO: Implement the logic to store the request in the database
        // You can use the DatabaseHandler class to add the request

        showToast("Request submitted successfully");
        finish(); // Optional: finish the current activity if needed
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
