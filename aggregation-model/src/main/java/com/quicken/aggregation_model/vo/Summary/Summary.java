package com.quicken.aggregation_model.vo.Summary;

import java.sql.Date;

public interface Summary {
    public double getExpenses();
    public double getIncome();
    public double getNet();
    public Date getDate();
}
