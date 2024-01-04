package com.example.registrecommobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClientSupportActivity extends AppCompatActivity {

    private static final String CUSTOMER_SUPPORT_NUMBER = "123456789"; // Replace with the actual customer support number

    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_support);

        Button callSupportButton = findViewById(R.id.buttonCallSupport);
        Button textSupportButton = findViewById(R.id.buttonTextSupport);
        editTextMessage = findViewById(R.id.editTextMessage);


        callSupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCustomerSupport();
            }
        });


        textSupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTextToSupport();
            }
        });
    }

    private void callCustomerSupport() {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + CUSTOMER_SUPPORT_NUMBER));
        startActivity(dialIntent);
    }

    private void sendTextToSupport() {
        String message = editTextMessage.getText().toString();

        if (!message.isEmpty()) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("smsto:" + CUSTOMER_SUPPORT_NUMBER));
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        } else {
            showToast("Please enter a message before sending.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
