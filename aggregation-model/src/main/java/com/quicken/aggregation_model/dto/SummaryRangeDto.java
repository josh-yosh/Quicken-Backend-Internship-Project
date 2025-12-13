package com.quicken.aggregation_model.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SummaryRangeDto {
    BigDecimal income;
    BigDecimal expenses;
    BigDecimal net;
    List<Date> dateRange;
}
