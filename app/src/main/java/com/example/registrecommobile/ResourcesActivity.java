package com.example.registrecommobile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourcesActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private Spinner dropdownSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        expandableListView = findViewById(R.id.expandableListView);
        dropdownSelector = findViewById(R.id.spinnerDropdownSelector);

        // Populate the dropdown with section names
        List<String> sections = new ArrayList<>();
        sections.add("FAQ");
        sections.add("Advice for Filling Forms");
        sections.add("Other Resources");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownSelector.setAdapter(adapter);

        // Set item selected listener for the dropdown
        dropdownSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle selection based on the chosen section
                String selectedSection = sections.get(position);
                displaySectionContent(selectedSection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        // Initialize and display default section content
        displaySectionContent("FAQ");
    }

    private void displaySectionContent(String section) {
        // Logic to display content based on the selected section
        if (section.equals("FAQ")) {
            // Display FAQ content using an expandable list view
            List<String> questions = new ArrayList<>();
            questions.add("What is the purpose of this app?");
            questions.add("How do I submit a request?");
            questions.add("Is my data secure?");
            questions.add("Can I edit my submitted request?");
            questions.add("How can I contact customer support?");

            List<String> answers = new ArrayList<>();
            answers.add("The app helps users with registration and submission of requests.");
            answers.add("You can submit a request through the 'Submit Request' section.");
            answers.add("Yes, we prioritize the security of your data.");
            answers.add("Once submitted, you cannot edit a request. Please contact support for assistance.");
            answers.add("You can contact customer support through the 'Client Support' section.");

            displayExpandableList(questions, answers);
        } else if (section.equals("Advice for Filling Forms")) {
            // Display advice for filling forms
            // You can customize this section based on your app's requirements
            // ...
        } else if (section.equals("Other Resources")) {
            // Display other resources
            // You can customize this section based on your app's requirements
            // ...
        }
    }

    private void displayExpandableList(List<String> questions, List<String> answers) {
        // Create a mapping of questions to their answers
        HashMap<String, String> data = new HashMap<>();
        for (int i = 0; i < questions.size(); i++) {
            data.put(questions.get(i), answers.get(i));
        }

        // Set up an expandable list view adapter
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, questions, data);
        expandableListView.setAdapter(expandableListAdapter);
    }
}
