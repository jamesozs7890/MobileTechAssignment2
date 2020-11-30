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
    //Declaration of Class, Layout
    ListView records;
    recordAdapter adapter;
    myRecords recordList;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialising & Declaration of the button
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
            //Get Data From addExpense
            String date = receiver.getString("Date");
            String amount = receiver.getString("Amount");
            String category = receiver.getString("Category");
            String choice = receiver.getString("Choice");
            String pMethod = receiver.getString("Payment");
            String desc = receiver.getString("Description");
            //Used for edit mode
            int positionEdited = receiver.getInt("edit");


            Expenses e = new Expenses(date,amount,category,choice,pMethod,desc);
            if(positionEdited >-1){
                recordList.getMyRecords().remove(positionEdited);
            }
            recordList.getMyRecords().add(e);


            adapter.notifyDataSetChanged();
            //Every time user go back to main activity after edit/add, show total expense
            double tAmount = 0;
            String total = "";
            for (int i =0;  i<adapter.getCount() ; i++){
                double value = Double.parseDouble(adapter.getItem(i).getAmount());
                tAmount += value;

            }
            total = String.format("%.2f", tAmount);
            Snackbar.make(coordinatorLayout , "Total Expense Spent: RM" + total, Snackbar.LENGTH_LONG).show();

        }
        //Button Listener
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
        records.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog(position);
                return true;
            }
        });
    }
    //function of retrieving data from list
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
    //Add Expense Function
    public void openAddExpense() {
        Intent intent = new Intent(this, addExpensesPage.class);
        startActivity(intent);
    }
    //Delete dialog(Confirmation)
    private void alertDialog(final int pos) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Do you sure you want to delete");
        dialog.setTitle( "Delete session");
        dialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        recordList.getMyRecords().remove(pos);
                        Toast toast=Toast.makeText(MainActivity.this,"Successfully Deleted",Toast.LENGTH_SHORT);
                        toast.show();
                        adapter.notifyDataSetChanged();
                    }
                });
        dialog.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}