package com.example.registrecommobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AdviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        // Get the advice list from resources
        String[] adviceList = getResources().getStringArray(R.array.advice_list);

        // Set up the ListView
        ListView listView = findViewById(R.id.listViewAdvice);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adviceList);
        listView.setAdapter(adapter);
    }
}
