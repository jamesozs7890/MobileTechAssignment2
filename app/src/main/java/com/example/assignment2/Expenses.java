package com.example.assignment2;

import java.util.Date;

public class Expenses {
    private Date expenseDate;
    private Double amount;
    private String category;
    private String category_choice;
    private String pMethod;
    private String description;
    public Expenses(Date expenseDate, Double amount , String category, String category_choice, String pMethod, String description  ) {
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category= category;
        this.category_choice = category_choice;
        this.pMethod = pMethod;
        this.description  = description ;
    }


    public String display() {
            String output = "Created: " + expenseDate +"\n Amount: " + amount + "\n Amount: " + amount;
            return output;
    }

    public void setExpenseDate(Date expenseDate) {
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

    public Date getExpenseDate() {
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

}
