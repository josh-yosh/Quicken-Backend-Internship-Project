package com.quicken.aggregation_model.service;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.model.Transaction;
import com.quicken.aggregation_model.repository.AccountRepo;
import com.quicken.aggregation_model.repository.TransactionRepo;
import com.quicken.aggregation_model.vo.Summary.SummaryMutable;
import com.quicken.aggregation_model.vo.Summary.SummaryVO;
import com.quicken.aggregation_model.vo.Summary.Summary;
import com.quicken.aggregation_model.vo.Summary.SummaryComparator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AggregationServiceImpl implements AggregationService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;
    Comparator<Summary> summaryComparator = SummaryComparator.SUMMARY_COMPARATOR;

    @Override
    public List<Account> getAllAccounts() {
        List<Account> allAccounts = accountRepo.findAll();
        return allAccounts;
    }

    @Override
    public List<SummaryVO> getAccountDailySummary(long AccountId, Date startDate, Date endDate) {
        List<Transaction> transactionsInRange = transactionRepo.getAllTransactionsFromAccountInDateRange(AccountId, startDate, endDate);
        Map<Date, SummaryMutable> dateToSummaryMutableMap = new HashMap<>();

        for(Transaction transaction: transactionsInRange){
            Date transactionDate = transaction.getDate();
            boolean dateIsAccounted = dateToSummaryMutableMap.containsKey(transactionDate);

            if(dateIsAccounted){
                SummaryMutable summaryMutable = dateToSummaryMutableMap.get(transactionDate);
                summaryMutable.addTransactionAmount(transaction.getAmount());
            } else {
                SummaryMutable newSummaryMutable = new SummaryMutable();
                newSummaryMutable.addTransactionAmount(transaction.getAmount());
                dateToSummaryMutableMap.put(transactionDate, newSummaryMutable);
            }
        }

        List<SummaryMutable> summaryMutables = List.copyOf(dateToSummaryMutableMap.values());
        List<SummaryVO> summaryVOs = new ArrayList<>();

        for(SummaryMutable summaryMutable: summaryMutables){
            summaryVOs.add(new SummaryVO(summaryMutable.getExpenses(), summaryMutable.getIncome(), summaryMutable.getNet(), summaryMutable.getDate()));
        }

        Collections.sort(summaryVOs, summaryComparator);
        return summaryVOs;
    }

    @Override
    public SummaryVO getAccountSummary(long id, Date startDate, Date endDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountSummary'");
    }

    
} 