package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.assignment2.R.id.cashRadioButton;
import static com.example.assignment2.R.id.creditCardRadioButton;
import static com.example.assignment2.R.id.eWalletRadioButton;


public class addExpensesPage extends AppCompatActivity {
    //Declaration
    String date,categoryValue,categoryChoiceValue,pMethod,description,amount;
    private RadioGroup payment;
    private RadioButton pType;

    int positionToEdit = -1;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses_page);

        //Initialise & Declaration of buttons,textview and more
        final Spinner category = findViewById(R.id.categorySpinner);
        final Spinner choice = findViewById(R.id.choiceSpinner);

        final String[] food= new String[] {"Breakfast", "Lunch","Dinner","Other"};
        final String[] entertainment = new String[] {"Gaming", "Movies","Activities","Other"};
        final String[] transport = new String[] {"Car", "Bus","Other"};

        final TextView dateEditText = findViewById(R.id.editTextDate);
        final EditText amountEditText = findViewById(R.id.amountEditText);
        final EditText descriptionEditText = findViewById(R.id.extraDescPlainText);

        final RadioButton cash =findViewById(cashRadioButton);
        final RadioButton eWallet = findViewById(eWalletRadioButton);
        final RadioButton creCard = findViewById(creditCardRadioButton);

        payment = findViewById(R.id.payment);
        Button clearBtn = findViewById(R.id.clearButton);
        Button saveBtn = findViewById(R.id.saveButton);
        Button backBtn = findViewById(R.id.backButton);

        //Initialise the radio button
        payment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    pType = findViewById(checkedId);
                }
            });

        //Show Calendar for user to choose
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        addExpensesPage.this, mDateSetListener, year,month,day);
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
        //Check if user is edit
        Bundle receiver = getIntent().getExtras();
        if(receiver!=null){
            //Get value when user go into edit mode
            String date = receiver.getString("Date");
            String amount = receiver.getString("Amount");
            String _category = receiver.getString("Category");
            String _choice = receiver.getString("Choice");
            String pMethod = receiver.getString("Payment");
            String desc = receiver.getString("Description");
            positionToEdit = receiver.getInt("edit");

            dateEditText.setText(date);
            amountEditText.setText(amount);

            switch (pMethod){
                case "Mobile Wallet":
                    eWallet.setChecked(true);
                    break;
                case "Credit Card":
                    creCard.setChecked(true);
                    break;
                default:
                    cash.setChecked(true);
                    break;
            }

            descriptionEditText.setText(desc);

        }

        //Save Button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Debugging
                boolean error = false;
                String msg ="List of Error: ";
                int i = 0;
                //Date Debug & initialise
                if (dateEditText.getText().toString().isEmpty()) {
                    error = true;
                    msg += " No Date Selection";
                    i +=1;
                } else{
                    date= dateEditText.getText().toString();
                }
                //Amount Debug & initialise
                if (amountEditText.getText().toString().isEmpty()) {
                    if (i != 0){
                        msg += ", No Amount Entered";
                    }else{
                        msg += "No Amount Entered";
                    }
                    i+=1;
                    error = true;
                } else{
                    if (amountEditText.getText().toString().startsWith(".") || amountEditText.getText().toString().startsWith("0")){
                        i+=1;
                        error = true;
                        if (i != 0){
                            msg += ", Invalid Number";
                        }else{
                            msg += " Invalid Number";
                        }
                    }else{
                        if(amountEditText.length() > 9 ){
                            i+=1;
                            error = true;
                            if (i != 0){
                                msg += ", Maximum a million";
                            }else {
                                msg += " Maximum a million";
                            }
                        }else{
                                double input = Double.parseDouble(amountEditText.getText().toString());
                                amount = String.format("%.2f", input );
                        }


                    }
                }
                // get selected radio button from radioGroup
                int selectedId = payment.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                pType = findViewById(selectedId);
                pMethod = pType.getText().toString();
                categoryValue = category.getSelectedItem().toString();
                categoryChoiceValue = choice.getSelectedItem().toString();
                //Description debug & initialise
                if (descriptionEditText.getText().toString().isEmpty()){
                    i+=1;
                    error = true;
                    if (i != 0){
                        msg += ", Empty description";
                    }else {
                        msg += "Empty description";
                    }
                }else{
                    description = descriptionEditText.getText().toString();
                }



                 /* System.out.println( date + "\n" + amount + "\n" + categoryValue + "\n"
                        + categoryChoiceValue+ "\n" + pMethod +"\n" + description);
                  */
                //Error will be true if input error and then value not pass
                if (error == false) {
                    Bundle bundle = new Bundle();

                    bundle.putString("Date", date);
                    bundle.putString("Amount", amount);
                    bundle.putString("Category", categoryValue);
                    bundle.putString("Choice", categoryChoiceValue);
                    bundle.putString("Payment", pMethod);
                    bundle.putString("Description", description);
                    bundle.putInt("edit",positionToEdit);

                    saveData(bundle);
                }else{
                    //Show error msg
                    alertDialog(msg,i);
                }
            }
        });
        //Buttons
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateEditText.setText(null);
                amountEditText.setText(null);
                descriptionEditText.setText(null);
                cash.setChecked(true);
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
    //Go to Activity
    public void naviBack(){
        Intent navi = new Intent(this,MainActivity.class);
        startActivity(navi);
    }
    //Save Data
    public void saveData(Bundle bundle){
        Intent save = new Intent(this,MainActivity.class);
        save.putExtras(bundle);
        startActivity(save);
    }
    //Error Msg
    private void alertDialog(String msg, int i) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(msg);
        dialog.setTitle( i + " Error Found!!!!");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Make sure no missing input",Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}