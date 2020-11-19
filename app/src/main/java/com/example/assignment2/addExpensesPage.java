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
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ArrayList<Expenses> list ;
    private RadioGroup paymentGroup;
    private RadioButton paymentType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses_page);

        final Spinner category = (Spinner) findViewById(R.id.categorySpinner);
        final Spinner choice = (Spinner) findViewById(R.id.choiceSpinner);
        final String[] food= new String[] {"Breakfast", "Lunch","Dinner","Other"};
        final String[] entertainment = new String[] {"Gaming", "Movies","Activities","Other"};
        final String[] transport = new String[] {"Car", "Bus","Other"};
        final TextView dateEditText = findViewById(R.id.editTextDate);
        final EditText amountEditText = findViewById(R.id.amountEditText);
        final EditText descriptionEditText = findViewById(R.id.extraDescPlainText);
        Button clearBtn = findViewById(R.id.clearButton);
        Button saveBtn = findViewById(R.id.saveButton);
        Button backBtn = findViewById(R.id.backButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date chosenDate = (Date) dateEditText.getText();
                Double amount = 1.0;
                String categoryValue = category.getSelectedItem().toString() ;
                String categoryChoiceValue =  choice.getSelectedItem().toString();
                paymentGroup = (RadioGroup) findViewById(R.id.payment);
                int typeId = paymentGroup.getCheckedRadioButtonId();
                paymentType = (RadioButton) findViewById(typeId);
                String pMethod = (String) paymentType.getText();
                String description = descriptionEditText.getText().toString();
                String text = amountEditText.getText().toString();
                if(!text.isEmpty()) {
                    try {
                        amount = Double.parseDouble(text);
                    } catch (Exception e1) {
                        //Enter a number
                    }
                }
                list.add( new Expenses(chosenDate,amount,categoryValue,categoryChoiceValue,pMethod,description) );
                Intent intent = new Intent(addExpensesPage.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("List", list);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
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
        final DatePickerDialog.OnDateSetListener finalMDateSetListener = mDateSetListener;
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        addExpensesPage.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("addExpensesPage", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dateEditText.setText(date);
            }
        };
        //Spinner
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Change the conversion spinner's items and others context based on user's input temperature
                switch(position){
                    case 0:
                        Toast.makeText(addExpensesPage.this,
                                "You have selected Kelvin", Toast.LENGTH_SHORT).show();
                        ArrayAdapter<String> foodChoice = new ArrayAdapter<String>(addExpensesPage.this,
                                android.R.layout.simple_spinner_item, food);
                        foodChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choice.setAdapter(foodChoice);
                        break;
                    case 1:
                        Toast.makeText(addExpensesPage.this, "You have selected Celsius", Toast.LENGTH_SHORT).show();
                        ArrayAdapter<String> entertainmentChoice = new ArrayAdapter<String>(addExpensesPage.this,
                                android.R.layout.simple_spinner_item, entertainment);
                        entertainmentChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choice.setAdapter(entertainmentChoice);
                        break;
                    case 2:
                        Toast.makeText(addExpensesPage.this, "Fahrenheit", Toast.LENGTH_SHORT).show();
                        ArrayAdapter<String> transportChoice = new ArrayAdapter<String>(addExpensesPage.this,
                                android.R.layout.simple_spinner_item, transport);
                        transportChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        choice.setAdapter(transportChoice);
                        break;

                }

            }
            //Nothing happen when nothing selected
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void naviMain(){
        Intent navi = new Intent(this,MainActivity.class);
        startActivity(navi);
    }
}