package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class Expenses extends BaseAdapter{
    private String expenseDate;
    /*private String amount;
    private String category;
    private String category_choice;
    private String pMethod;
    private String description;*/
    LayoutInflater inflter;

    public Expenses(Context applicationContext, String expenseDate){
        this.expenseDate = expenseDate;
        /*this.amount = amount;
        this.category= category;
        this.category_choice = category_choice;
        this.pMethod = pMethod;
        this.description  = description;*/
        inflter = (LayoutInflater.from(applicationContext));
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
        /*view =  inflter.inflate(R.layout.activity_main,null);
        TextView title = (TextView) view.findViewById(R.id.listViewName);*/
        return null;
    }
}
