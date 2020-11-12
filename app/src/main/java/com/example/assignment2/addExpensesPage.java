package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addExpensesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses_page);

        final EditText dateEditText = findViewById(R.id.editTextDate);
        final EditText amountEditText = findViewById(R.id.amountEditText);
        final EditText descriptionEditText = findViewById(R.id.extraDescPlainText);
        Button clearBtn = findViewById(R.id.clearButton);
        Button backBtn = findViewById(R.id.backButton);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateEditText.setText(null);
                amountEditText.setText(null);
                descriptionEditText.setText(null);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naviMain();
            }
        });
    }
    public void naviMain(){
        Intent navi = new Intent(this,MainActivity.class);
        startActivity(navi);
    }
}