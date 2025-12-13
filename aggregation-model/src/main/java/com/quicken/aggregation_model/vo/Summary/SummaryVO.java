package com.quicken.aggregation_model.vo.Summary;

import java.math.BigDecimal;
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
public class SummaryVO extends SummaryAbstract implements Summary{
    final int LESS_THAN = -1;

    //pre: true
    //post: if amount < 0, expenses + amount; 
    //post: if amount > 0, income + amount; 
    //post: net + amount;
    public void addTransactionAmount(BigDecimal amount) {
        boolean isExpense = amount.compareTo(BigDecimal.valueOf(0)) == LESS_THAN;
        if(isExpense){
            expenses = expenses.add(amount);
        } else {
            income = income.add(amount);
        }
        net = net.add(amount);
    }

    public SummaryVO(Date startDate, Date endDate){
        this.income = new BigDecimal(0);
        this.expenses = new BigDecimal(0);
        this.net = new BigDecimal(0);
        this.dateRange = List.of(startDate, endDate);
    }

    public SummaryVO(BigDecimal income, BigDecimal expenses, BigDecimal net, Date startDate){
        this.income = income;
        this.expenses = expenses;
        this.net = net;
        this.dateRange = List.of(startDate, startDate);
    }
    
    public SummaryVO(BigDecimal income, BigDecimal expenses, BigDecimal net, Date startDate, Date endDate){
        this.income = income;
        this.expenses = expenses;
        this.net = net;
        this.dateRange = List.of(startDate, endDate);
    }
}
