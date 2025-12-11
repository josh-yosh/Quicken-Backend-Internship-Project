package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SummaryRangeVO extends SummaryAbstract implements Summary{
    Date startDate;
    Date endDate;

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
        return startDate;
    }

    public List<Date> getDateRange(){
        List<Date> dateRange = List.of(startDate, endDate);
        return dateRange;
    }

    public SummaryRangeVO(Date startDate, Date endDate){
        this.income = 0;
        this.expenses = 0;
        this.net = 0;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SummaryRangeVO(double income, double expenses, double net, Date startDate, Date endDate){
        this.income = income;
        this.expenses = expenses;
        this.net = net;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
}
