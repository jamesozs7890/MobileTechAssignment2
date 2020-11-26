package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class addExpensesPage extends AppCompatActivity {

    String date,amount,categoryValue,categoryChoiceValue,pMethod,description;
    private RadioGroup payment;
    private RadioButton pType;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses_page);


        final Spinner category = findViewById(R.id.categorySpinner);
        final Spinner choice = findViewById(R.id.choiceSpinner);

        final String[] food= new String[] {"Breakfast", "Lunch","Dinner","Other"};
        final String[] entertainment = new String[] {"Gaming", "Movies","Activities","Other"};
        final String[] transport = new String[] {"Car", "Bus","Other"};

        final TextView dateEditText = findViewById(R.id.editTextDate);
        final EditText amountEditText = findViewById(R.id.amountEditText);
        final EditText descriptionEditText = findViewById(R.id.extraDescPlainText);

        Button clearBtn = findViewById(R.id.clearButton);
        Button saveBtn = findViewById(R.id.saveButton);
        Button backBtn = findViewById(R.id.backButton);
        payment = findViewById(R.id.payment);

        payment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pType = findViewById(checkedId);
            }
        });

        //final DatePickerDialog.OnDateSetListener finalMDateSetListener = mDateSetListener;
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        addExpensesPage.this, mDateSetListener, year,month,day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
            
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("addExpensesPage", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;

                dateEditText.setText(date);
            }
        };



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date= dateEditText.getText().toString();

                amount = amountEditText.getText().toString();

                categoryValue = category.getSelectedItem().toString();

                categoryChoiceValue = choice.getSelectedItem().toString();

                if(!pType.isChecked()){
                    pMethod = "not Selected";
                }else{
                    pMethod = pType.getText().toString();
                }

                description = descriptionEditText.getText().toString();


                System.out.println( date + "\n" + amount + "\n" + categoryValue + "\n"
                        + categoryChoiceValue+ "\n" + pMethod +"\n" + description);


                Bundle bundle = new Bundle();

                bundle.putString("Date",date);
                bundle.putString("Amount",amount);
                bundle.putString("Category",categoryValue);
                bundle.putString("Choice",categoryChoiceValue);
                bundle.putString("Payment",pMethod);
                bundle.putString("Description",description);

            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateEditText.setText(null);
                amountEditText.setText(null);
                descriptionEditText.setText(null);
                pType.setChecked(false);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naviBack();
            }
        });

        //Spinner
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        ArrayAdapter<String> foodChoice = new ArrayAdapter<>(addExpensesPage.this,
                                android.R.layout.simple_spinner_item, food);
                        foodChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choice.setAdapter(foodChoice);
                        break;
                    case 1:
                        ArrayAdapter<String> entertainmentChoice = new ArrayAdapter<>(addExpensesPage.this,
                                android.R.layout.simple_spinner_item, entertainment);
                        entertainmentChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choice.setAdapter(entertainmentChoice);
                        break;
                    case 2:
                        ArrayAdapter<String> transportChoice = new ArrayAdapter<>(addExpensesPage.this,
                                android.R.layout.simple_spinner_item, transport);
                        transportChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choice.setAdapter(transportChoice);
                        break;
                }
            }

            //Nothing happen when nothing selected
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });
    }

    public void naviBack(){
        Intent navi = new Intent(this,MainActivity.class);
        startActivity(navi);
    }

    public void saveData(Bundle bundle){
        Intent save = new Intent(this,MainActivity.class);
        save.putExtras(bundle);
        startActivity(save);
    }
}