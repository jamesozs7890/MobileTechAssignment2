package com.example.assignment2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView records;
    recordAdapter adapter;
    myRecords recordList;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.ExpensesToolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
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
            int positionEdited = receiver.getInt("edit");


            Expenses e = new Expenses(date,amount,category,choice,pMethod,desc);
            if(positionEdited >-1){
                recordList.getMyRecords().remove(positionEdited);
            }
            recordList.getMyRecords().add(e);


            adapter.notifyDataSetChanged();
            double tAmount = 0;
            String total = "";
            for (int i =0;  i<adapter.getCount() ; i++){
                double value = Double.parseDouble(adapter.getItem(i).getAmount());
                tAmount += value;

            }
            total = String.format("%.2f", tAmount);
            Snackbar.make(coordinatorLayout , "Total Expense Spent: RM" + total, Snackbar.LENGTH_LONG).show();
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
        alertDialog(pos);
    }

    public void openAddExpense() {
        Intent intent = new Intent(this, addExpensesPage.class);
        startActivity(intent);
    }
    private void alertDialog(final int pos) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Please choose a session");
        dialog.setTitle( " Mode");
        dialog.setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
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
                });
        dialog.setNegativeButton(
                "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        recordList.getMyRecords().remove(pos);
                        finish();
                        startActivity(getIntent());
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}