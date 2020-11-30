package com.example.assignment2;

import android.app.Application;


//a modal class
public class myApp extends Application {
    private myRecords records = new myRecords();

    public myRecords getRecords() {
        return records;
    }
}
