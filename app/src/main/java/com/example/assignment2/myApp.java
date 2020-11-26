package com.example.assignment2;

import android.app.Application;

public class myApp extends Application {
    private myRecords records = new myRecords();

    public myRecords getRecords() {
        return records;
    }

    public void setRecords(myRecords records) {
        this.records = records;
    }
    
}
