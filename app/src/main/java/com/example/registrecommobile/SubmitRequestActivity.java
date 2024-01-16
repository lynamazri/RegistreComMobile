package com.example.registrecommobile;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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

        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
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



        if (userId != -1) {

            if (companyName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() ||
                    emailAddress.isEmpty() || activityType.isEmpty() || fullName.isEmpty() ||
                    dateOfBirth.isEmpty() || nationality.isEmpty() || idNumber.isEmpty() ||
                    !declarationChecked) {
                showToast("Please fill in all mandatory fields and accept the declaration.");
            } else {

                DatabaseHandler db = new DatabaseHandler(this);

                Request existingRequest = db.getRequestByUserId(userId);

                if (existingRequest != null) {
                    showToast("Cannot submit another request. You already have a pending request.");
                    startActivity(new Intent(SubmitRequestActivity.this, ViewRequestStatusActivity.class));
                } else {
                    Request newRequest = new Request(companyName, address, phoneNumber, emailAddress,
                            activityType, fullName, dateOfBirth, nationality, idNumber, "Pending", userId);


                    db.addRequest(newRequest);

                    showToast("Request submitted successfully");
                    finish();
                }



            }

        } else {
            showToast("User session expired. Please log in again.");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }






    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        editTextDateOfBirth.setText(String.format("%02d/%02d/%04d", month + 1, dayOfMonth, year));
                    }
                },
                year, month, day);


        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }
}
