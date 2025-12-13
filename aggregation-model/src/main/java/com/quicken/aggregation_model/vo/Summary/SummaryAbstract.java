package com.quicken.aggregation_model.vo.Summary;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class SummaryAbstract{
    BigDecimal income;
    BigDecimal expenses;
    BigDecimal net;
}
