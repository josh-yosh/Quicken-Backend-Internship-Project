package com.quicken.aggregation_model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SummaryVO {
    long AccountId;
    double expenses;
    double income;
    double net;

}
