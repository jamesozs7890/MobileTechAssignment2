package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Expenses> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.ExpensesToolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.AddButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddExpense();
            }
        });
    }

    public void openAddExpense() {
        Intent intent = new Intent(this, addExpensesPage.class);
        startActivity(intent);
    }
    @Override
    public void onActivityResult(int requestCode,  int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                Bundle bundleObject = getIntent().getExtras();
                list = (ArrayList<Expenses>) bundleObject.getSerializable("List");
                TextView dynamicTextView = new TextView(this);
                dynamicTextView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                for (int i = 0;i< list.size();i++) {
                    dynamicTextView.setText(list.get(i).display());
                }
            }
    }
}