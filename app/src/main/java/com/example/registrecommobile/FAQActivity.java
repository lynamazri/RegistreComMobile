package com.example.registrecommobile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FAQActivity extends AppCompatActivity {

    private Spinner questionSpinner;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        questionSpinner = findViewById(R.id.questionSpinner);
        answerTextView = findViewById(R.id.answerTextView);

        ArrayAdapter<CharSequence> questionAdapter = ArrayAdapter.createFromResource(
                this, R.array.faq_questions, android.R.layout.simple_spinner_item);
        questionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionSpinner.setAdapter(questionAdapter);
        questionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedQuestion = parentView.getItemAtPosition(position).toString();
                updateAnswer(selectedQuestion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    private void updateAnswer(String selectedQuestion) {

        String[] faqAnswers = getResources().getStringArray(R.array.faq_answers);

        int index = -1;
        for (int i = 0; i < getResources().getStringArray(R.array.faq_questions).length; i++) {
            if (getResources().getStringArray(R.array.faq_questions)[i].equals(selectedQuestion)) {
                index = i;
                break;
            }
        }

        if (index != -1 && index < faqAnswers.length) {
            String selectedAnswer = faqAnswers[index];
            answerTextView.setText(selectedAnswer);
        }
    }
}
