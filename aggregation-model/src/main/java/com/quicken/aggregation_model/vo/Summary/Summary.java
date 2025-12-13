package com.quicken.aggregation_model.vo.Summary;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface Summary {
    public BigDecimal getExpenses();
    public BigDecimal getIncome();
    public BigDecimal getNet();
    public List<Date> getDateRange(); //debating on whether getDate should be getStartDate, trying to make it applicable for both daily summary and range summary

    public void addTransactionAmount(BigDecimal amount);
}
