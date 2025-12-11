package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;

public interface Summary {
    public double getExpenses();
    public double getIncome();
    public double getNet();
    public Date getDate(); //debating on whether getDate should be getStartDate, trying to make it applicable for both daily summary and range summary

    public void addTransactionAmount(double amount);
}
