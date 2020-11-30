package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class Expenses{
    //Declaration of class
    private String expenseDate;
    private String amount;
    private String category;
    private String category_choice;
    private String pMethod;
    private String description;
    //Constructor
    public Expenses(String expenseDate, String amount, String category, String category_choice, String pMethod, String description) {
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category = category;
        this.category_choice = category_choice;
        this.pMethod = pMethod;
        this.description = description;
    }
    //Getter And Setter
    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_choice() {
        return category_choice;
    }

    public void setCategory_choice(String category_choice) {
        this.category_choice = category_choice;
    }

    public String getpMethod() {
        return pMethod;
    }

    public void setpMethod(String pMethod) {
        this.pMethod = pMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
