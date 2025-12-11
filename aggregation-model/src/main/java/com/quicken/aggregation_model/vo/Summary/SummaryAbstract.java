package com.quicken.aggregation_model.vo.Summary;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public abstract class SummaryAbstract{
    BigDecimal income;
    BigDecimal expenses;
    BigDecimal net;
}
