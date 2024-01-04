package com.example.registrecommobile;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        Button faqButton = findViewById(R.id.faqButton);
        Button adviceButton = findViewById(R.id.adviceButton);
        Button otherResourcesButton = findViewById(R.id.otherResourcesButton);

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResourcesActivity.this, FAQActivity.class));
            }
        });

        adviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResourcesActivity.this, AdviceActivity.class));
            }
        });

        otherResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResourcesActivity.this, OtherResourcesActivity.class));
            }
        });
    }
}
