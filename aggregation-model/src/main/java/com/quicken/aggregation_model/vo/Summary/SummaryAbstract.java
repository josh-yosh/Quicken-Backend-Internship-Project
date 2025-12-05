package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;
import lombok.Getter;

@Getter
public abstract class SummaryAbstract{
    double income;
    double expenses;
    double net;
    Date date;
}
