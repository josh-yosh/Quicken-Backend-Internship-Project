package com.quicken.aggregation_model.vo.Summary;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SummaryMutable extends SummaryAbstract implements Summary{

    //pre: true
    //post: if amount < 0, expenses + amount; 
    //post: if amount > 0, income + amount; 
    //post: net + amout;
    public void addTransactionAmount(double amount) {
        boolean isExpense = amount < 0;
        if(isExpense){
            this.expenses += amount;
        } else {
            this.income += amount;
        }
        net += amount;
    }
}
