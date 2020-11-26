package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.AdapterView;
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
            Double amount = receiver.getDouble("Amount");
            String category = receiver.getString("Category");
            String choice = receiver.getString("Choice");
            String pMethod = receiver.getString("Payment");
            String desc = receiver.getString("Description");
            int positionEdited = receiver.getInt("edit");


            Expenses e = new Expenses(date,amount,category,choice,pMethod,desc);
            if(positionEdited >-1){
                recordList.getMyRecords().remove(positionEdited);
            }
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

        records.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewRecord(position);
            }
        });
    }

    public void viewRecord(int pos){
        Intent i = new Intent(getApplicationContext(),addExpensesPage.class);

        Expenses _e = recordList.getMyRecords().get(pos);

        i.putExtra("Edit",pos);
        i.putExtra("Date",_e.getExpenseDate());
        i.putExtra("Amount",_e.getAmount());
        i.putExtra("Category",_e.getCategory());
        i.putExtra("Choice",_e.getCategory_choice());
        i.putExtra("Payment",_e.getpMethod());
        i.putExtra("Description",_e.getDescription());

        startActivity(i);
    }

    public void openAddExpense() {
        Intent intent = new Intent(this, addExpensesPage.class);
        startActivity(intent);
    }
}