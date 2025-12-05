package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;

public class SummaryVO extends SummaryAbstract implements Summary{
    
    public SummaryVO(double income, double expenses, double net, Date date){
        this.income = income;
        this.expenses = expenses;
        this.net = net;
        this.date = date;
    }
}
