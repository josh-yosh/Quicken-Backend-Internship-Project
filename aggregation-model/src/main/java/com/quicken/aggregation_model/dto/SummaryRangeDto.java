package com.quicken.aggregation_model.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SummaryRangeDto {
    BigDecimal income;
    BigDecimal expenses;
    BigDecimal net;
    Date startDate;
    Date endDate;
}
