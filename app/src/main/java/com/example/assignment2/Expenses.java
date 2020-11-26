package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class Expenses{

    private String expenseDate;
    private Double amount;
    private String category;
    private String category_choice;
    private String pMethod;
    private String description;

    public Expenses(String expenseDate, Double amount, String category, String category_choice, String pMethod, String description) {
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category = category;
        this.category_choice = category_choice;
        this.pMethod = pMethod;
        this.description = description;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
