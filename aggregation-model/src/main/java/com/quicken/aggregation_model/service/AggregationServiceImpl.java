package com.quicken.aggregation_model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.quicken.aggregation_model.exceptions.AccountNotFoundException;
import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.model.Transaction;
import com.quicken.aggregation_model.repository.AccountRepo;
import com.quicken.aggregation_model.repository.TransactionRepo;
import com.quicken.aggregation_model.vo.Summary.SummaryDailyVO;
import com.quicken.aggregation_model.vo.Summary.SummaryRangeVO;
import com.quicken.aggregation_model.vo.Summary.Summary;
import com.quicken.aggregation_model.vo.Summary.SummaryComparator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
    public Summary getAccountSummary(long accountId, Date startDate, Date endDate) {
        Account account = accountRepo.findAccountbyId(accountId); //to check if Account exists
        if(account == null) //should the repo layer throw an exception? or here?
             throw new AccountNotFoundException(accountId);


        List<Transaction> transactionsInRange = transactionRepo.getAllTransactionsFromAccountInDateRange(accountId, startDate, endDate);

        double expenses = 0;
        double income = 0;
        double net = 0;

        for(Transaction transaction: transactionsInRange){
            double transactionAmount = transaction.getAmount();
            boolean isExpense = transactionAmount < 0;
            
            if(isExpense){
                expenses += transactionAmount;
            } else {
                income += transactionAmount;
            }
            net += transactionAmount;
        }

        Summary summary = new SummaryRangeVO(income, expenses, net, startDate, endDate);

        return summary;
    }

    @Override
    public List<Summary> getAccountDailySummary(long accountId, Date startDate, Date endDate) {
        Account account = accountRepo.findAccountbyId(accountId); //to check if Account exists
        if(account == null)
             throw new AccountNotFoundException(accountId);

        List<Transaction> transactionsInRange = transactionRepo.getAllTransactionsFromAccountInDateRange(accountId, startDate, endDate);
        Map<Date, Summary> dateToSummaryVoMap = new HashMap<>();

        //Iterate through all the transactions given from query
        for(Transaction transaction: transactionsInRange){
            Date transactionDate = transaction.getDate();
            boolean dateIsAccounted = dateToSummaryVoMap.containsKey(transactionDate);

            //check if date is in map to SummaryMutable and then update if is in. If not, add new SummaryMutable in map
            if(dateIsAccounted){
                Summary summaryVO = dateToSummaryVoMap.get(transactionDate);
                summaryVO.addTransactionAmount(transaction.getAmount());
                dateToSummaryVoMap.put(transactionDate, summaryVO);
            } else {
                Summary newSummaryVO = new SummaryDailyVO(transactionDate);
                newSummaryVO.addTransactionAmount(transaction.getAmount());
                dateToSummaryVoMap.put(transactionDate, newSummaryVO);
            }
        }

        //Create into List inorder to convert summaryMutables into SummaryVO's
        List<Summary> summaryVOs = new ArrayList<>(dateToSummaryVoMap.values());

        //Sort by Date
        if(summaryVOs.size() > 0)
            Collections.sort(summaryVOs, summaryComparator);

        return summaryVOs;
    }
} 