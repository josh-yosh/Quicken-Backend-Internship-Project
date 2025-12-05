package com.quicken.aggregation_model.service;

import java.sql.Date;
import java.util.List;

import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.vo.SummaryVO;

public interface AggregationService {
    //pre: true
    //part of post: if user has no Account, returns empty list
    List<Account> getAllAccounts();

    //pre: account id must exist
    //post: a SummaryVO with int values income, expenses, net.
    SummaryVO getAccountSummary(long AccountId);

    //pre: account id must exist
    //pre: startDate and endDate have to be valid dates
    //post: a SummaryVO with int values income, expenses, net.
    //post: a timeRange with no transactions will return a SummaryVO with 0's income, expenses, net values.
    SummaryVO getAccountSummary(long id, Date startDate, Date endDate);
}
