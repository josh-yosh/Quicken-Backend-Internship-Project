package com.quicken.aggregation_model.vo.Summary;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

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
    List<Date> dateRange;
}
