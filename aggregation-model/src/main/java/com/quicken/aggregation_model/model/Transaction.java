package com.quicken.aggregation_model.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter //should transaction be immutable?
@Getter
@EqualsAndHashCode
public class Transaction {
    private long id;
    private long accountId;
    private Date date;
    private BigDecimal amount;
    private String description; 
}
