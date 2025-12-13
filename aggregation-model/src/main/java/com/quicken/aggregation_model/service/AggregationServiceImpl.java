package com.quicken.aggregation_model.service;

import java.math.BigDecimal;
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
import com.quicken.aggregation_model.vo.Summary.SummaryVO;
import com.quicken.aggregation_model.vo.Summary.Summary;
import com.quicken.aggregation_model.vo.Summary.SummaryComparator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AggregationServiceImpl implements AggregationService {

    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;
    Comparator<Summary> summaryComparator = SummaryComparator.SUMMARY_COMPARATOR;
    final int LESS_THAN = -1;


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

        BigDecimal expenses = new BigDecimal(0);
        BigDecimal income = new BigDecimal(0);
        BigDecimal net = new BigDecimal(0);

        for(Transaction transaction: transactionsInRange){
            BigDecimal transactionAmount = transaction.getAmount();
            boolean isExpense = transactionAmount.compareTo(BigDecimal.valueOf(0)) == LESS_THAN; 
            
            if(isExpense){
                expenses = expenses.add(transactionAmount);
            } else {
                income = income.add(transactionAmount);
            }
            net = net.add(transactionAmount);
        }

        Summary summary = new SummaryVO(income, expenses, net, startDate, endDate);

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
                Summary newSummaryVO = new SummaryVO(transactionDate, transactionDate);
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