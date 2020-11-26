package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView records;
    recordAdapter adapter;
    myRecords recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.ExpensesToolbar);
        setSupportActionBar(toolbar);

        records = findViewById(R.id.recordListView);

        recordList = ((myApp)this.getApplication()).getRecords();

        adapter = new recordAdapter(MainActivity.this,recordList);
        records.setAdapter(adapter);

        //listen,capture,create,add
        Bundle receiver = getIntent().getExtras();
        if(receiver != null){
            String date = receiver.getString("Date");
            String amount = receiver.getString("Amount");
            String category = receiver.getString("Category");
            String choice = receiver.getString("Choice");
            String pMethod = receiver.getString("Payment");
            String desc = receiver.getString("Description");

            Expenses e = new Expenses(date,amount,category,choice,pMethod,desc);

            recordList.getMyRecords().add(e);

            adapter.notifyDataSetChanged();
        }

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
}