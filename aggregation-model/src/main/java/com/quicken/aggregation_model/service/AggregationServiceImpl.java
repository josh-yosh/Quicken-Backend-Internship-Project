package com.quicken.aggregation_model.service;

import java.sql.Date;
import java.util.List;

import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.repository.AccountRepo;
import com.quicken.aggregation_model.repository.TransactionRepo;
import com.quicken.aggregation_model.vo.SummaryVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AggregationServiceImpl implements AggregationService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;


    @Override
    public List<Account> getAllAccounts() {
        List<Account> allAccounts = accountRepo.findAll();
        return allAccounts;
    }

    @Override
    public SummaryVO getAccountSummary(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountSummary'");
    }

    @Override
    public SummaryVO getAccountSummary(long id, Date startDate, Date endDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountSummary'");
    }

    
} 