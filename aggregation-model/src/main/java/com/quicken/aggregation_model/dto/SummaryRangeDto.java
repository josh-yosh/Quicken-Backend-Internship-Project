package com.quicken.aggregation_model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SummaryRangeDto {
    double income;
    double expenses;
    double net;
    Date startDate;
    Date endDate;
}
