package com.example.registrecommobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewRequestStatusActivity extends AppCompatActivity {

    private TextView textViewCompanyName, textViewAddress, textViewActivityType, textViewPersonalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_status);

        textViewCompanyName = findViewById(R.id.textViewCompanyName);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewActivityType = findViewById(R.id.textViewActivityType);
        textViewPersonalInfo = findViewById(R.id.textViewPersonalInfo);


}
}
