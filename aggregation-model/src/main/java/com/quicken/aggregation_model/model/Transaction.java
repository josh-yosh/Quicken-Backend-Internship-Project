package com.quicken.aggregation_model.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Transaction {
    private long id;
    private long accountId;
    private Date date;
    private double amount;
    private String description; 
}
