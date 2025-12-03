package com.quicken.aggregation_model.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Transaction {
    private Date date;
    private double amount;
    private String description; 
}
