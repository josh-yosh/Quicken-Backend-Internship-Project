package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SummaryDailyVO extends SummaryAbstract implements Summary{
    Date date;

    //pre: true
    //post: if amount < 0, expenses + amount; 
    //post: if amount > 0, income + amount; 
    //post: net + amount;
    public void addTransactionAmount(double amount) {
        boolean isExpense = amount < 0;
        if(isExpense){
            this.expenses += amount;
        } else {
            this.income += amount;
        }
        net += amount;
    }

    public Date getDate(){
        return date;
    }

    public SummaryDailyVO(Date date){
        this.income = 0;
        this.expenses = 0;
        this.net = 0;
        this.date = date;
    }
    
    public SummaryDailyVO(double income, double expenses, double net, Date date){
        this.income = income;
        this.expenses = expenses;
        this.net = net;
        this.date = date;
    }
}
