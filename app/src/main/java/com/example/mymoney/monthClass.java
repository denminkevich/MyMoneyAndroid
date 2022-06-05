package com.example.mymoney;

import java.time.LocalDate;
import java.util.ArrayList;

public class monthClass {
    private LocalDate date;
    private double countExpInc;
    private ArrayList<ExpensesGroup> monthExp;
    private ArrayList<IncomesGroup> monthInc;

    public monthClass(LocalDate date, double countExpInc, ArrayList<ExpensesGroup> monthExp, ArrayList<IncomesGroup> monthInc) {
        this.date = date;
        this.countExpInc = countExpInc;
        this.monthExp = monthExp;
        this.monthInc = monthInc;
    }

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCount() {return countExpInc;}

    public void setCount(Double count) {
        this.countExpInc = count;
    }

    public ArrayList getExpenses() {return monthExp;}

    public void setExpenses(ArrayList<ExpensesGroup> expGroup) {
        this.monthExp = expGroup;
    }

    public ArrayList getIncomes() {return monthInc;}

    public void setIncomes(ArrayList<IncomesGroup> incGroup) {
        this.monthInc = incGroup;
    }
}
