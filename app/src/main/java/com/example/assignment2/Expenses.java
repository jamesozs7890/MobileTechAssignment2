package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Date;

public class Expenses extends BaseAdapter{
    private String expenseDate;
    private Double amount;
    private String category;
    private String category_choice;
    private String pMethod;
    private String description;
    LayoutInflater inflter;

    public Expenses(Context applicationContext, String expenseDate, Double amount , String category, String category_choice, String pMethod, String description  ){
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category= category;
        this.category_choice = category_choice;
        this.pMethod = pMethod;
        this.description  = description ;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public String display() {
            String output = "Created: " + expenseDate +"\n Amount: " + amount + "\n Amount: " + amount;
            return output;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategory_choice(String category_choice) {
        this.category_choice = category_choice;
    }

    public void setpMethod(String pMethod) {
        this.pMethod = pMethod;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpenseDate() {
        return expenseDate;
    }
    public Double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getCategory_choice() {
        return category_choice;
    }

    public String getpMethod() {
        return pMethod;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup vGroup) {
        view =  inflter.inflate(R.layout.activity_main,null);

        return null;
    }
}
