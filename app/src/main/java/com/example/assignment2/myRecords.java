package com.example.assignment2;

import java.util.ArrayList;
import java.util.List;

public class myRecords {

    List<Expenses> myRecords;
    Expenses _e;


    public myRecords(List<Expenses> myRecords) {
        this.myRecords = myRecords;
    }

    public myRecords(){
        this.myRecords = new ArrayList<>();

        this.myRecords = new ArrayList<>();

        String[] recordDate = new String[0];
        
        for(int i=0;i<recordDate.length;i++){
            myRecords.add(_e);
        }
    }

    public List<Expenses> getMyRecords() {
        return myRecords;
    }

    public void setMyRecords(List<Expenses> myRecords) {
        this.myRecords = myRecords;
    }
}
