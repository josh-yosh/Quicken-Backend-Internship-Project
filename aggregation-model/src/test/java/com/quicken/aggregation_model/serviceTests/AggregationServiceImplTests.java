package com.quicken.aggregation_model.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.model.Transaction;
import com.quicken.aggregation_model.repository.AccountRepo;
import com.quicken.aggregation_model.repository.TransactionRepo;
import com.quicken.aggregation_model.service.AggregationServiceImpl;
import com.quicken.aggregation_model.vo.Summary.Summary;
import com.quicken.aggregation_model.vo.Summary.SummaryDailyVO;
import com.quicken.aggregation_model.vo.Summary.SummaryRangeVO;


public class AggregationServiceImplTests {

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private TransactionRepo transactionRepo;

    private AggregationServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new AggregationServiceImpl(accountRepo, transactionRepo);
    }

    // public List<Summary> getAccountDailySummary(long AccountId, Date startDate, Date endDate)
    //public abstract class SummaryAbstract
    //double income;
    //double expenses;
    //double net;
    //Date date;

    // (2001,2,'2024-01-03',5000.00,'Online store sales'),
    // (2001,2,'2024-01-03',5000.00,'Online store sales'),
    // (2002,2,'2024-01-05',-1200.00,'Office rent'),
    // (2002,2,'2024-01-05',-1200.00,'Office rent'),
    // (2003,2,'2024-01-07',-350.75,'Office supplies'),
    @Test
    void getAccountDailySummary_MultipleDays() {
        long accountId = 2L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(new Account(accountId, "Test", description));

        // Mock transactions
        List<Transaction> mockTx = List.of(
            new Transaction(1, accountId, Date.valueOf("2024-01-03"), 5000.0, description),
            new Transaction(2, accountId, Date.valueOf("2024-01-03"), 5000.0, description),
            new Transaction(3, accountId, Date.valueOf("2024-01-05"), -1200.0, description),
            new Transaction(4, accountId, Date.valueOf("2024-01-05"), -1200.0, description),
            new Transaction(5, accountId, Date.valueOf("2024-01-07"), -350.75, description)
        );
        
        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-03"),
            Date.valueOf("2024-01-07")
        )).thenReturn(mockTx);

        Summary summary1 = new SummaryDailyVO(10000, 0.0, 10000.0, Date.valueOf("2024-01-03"));
        Summary summary2 = new SummaryDailyVO(0, -2400.0, -2400.0, Date.valueOf("2024-01-05"));
        Summary summary3 = new SummaryDailyVO(0, -350.75, -350.75, Date.valueOf("2024-01-07"));

        List<Summary> expectedSummaries = new ArrayList<>(List.of(summary1, summary2, summary3));
        List<Summary> summaries = service.getAccountDailySummary(2L, Date.valueOf("2024-01-03"), Date.valueOf("2024-01-07"));
        
        assertEquals(expectedSummaries, summaries, "ExpectedSummary: " + expectedSummaries.toString() + "\nActual: " + summaries.toString());
    }
    
    @Test
    void getAccountDailySummary_oneDay() {
        long accountId = 2L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(new Account(accountId, "Test", description));

        // Mock transactions
        List<Transaction> mockTx = List.of(
            new Transaction(1, accountId, Date.valueOf("2024-01-03"), 5000.0, description),
            new Transaction(2, accountId, Date.valueOf("2024-01-03"), 5000.0, description)
        );
        
        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-03"),
            Date.valueOf("2024-01-03")
        )).thenReturn(mockTx);

        Summary summary1 = new SummaryDailyVO(10000, 0, 10000, Date.valueOf("2024-01-03"));

        List<Summary> expectedSummaries = List.of(summary1);
        List<Summary> summaries = service.getAccountDailySummary(accountId, Date.valueOf("2024-01-03"), Date.valueOf("2024-01-03"));
        assertEquals(summaries, expectedSummaries);
    }

    @Test
    void getAccountDailySummary_noDay() {
        long accountId = 2L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(new Account(accountId, "Test", description));

        // Mock transactions
        List<Transaction> mockTx = List.of(
        );

        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-01"),
            Date.valueOf("2024-01-01")
        )).thenReturn(mockTx);

        List<Summary> expectedSummaries = List.of();
        List<Summary> summaries = service.getAccountDailySummary(accountId, Date.valueOf("2024-01-01"), Date.valueOf("2024-01-01"));
        assertEquals(summaries, expectedSummaries);
    }

    @Test
    void getAccountDailySummary_InvalidAccount() {
        long accountId = 4L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(null);

        // Mock transactions
        List<Transaction> mockTx = List.of(
        );

        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-01"),
            Date.valueOf("2024-01-01")
        )).thenReturn(mockTx);

        assertThrows(RuntimeException.class, () -> {
            List<Summary> summaries = service.getAccountDailySummary(accountId, Date.valueOf("2024-01-01"), Date.valueOf("2024-01-01"));
        });
    }

    // Range of Dates Summary function

    @Test
    void getAccountRangeSummary_MultipleDays() {
        long accountId = 1L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(new Account(accountId, "Test", description));

        // Mock transactions
        List<Transaction> mockTx = List.of(
            new Transaction(1, accountId, Date.valueOf("2024-01-03"), 5000.0, description),
            new Transaction(2, accountId, Date.valueOf("2024-01-03"), 5000.0, description),
            new Transaction(3, accountId, Date.valueOf("2024-01-05"), -1000.0, description),
            new Transaction(4, accountId, Date.valueOf("2024-01-05"), -1000.0, description),
            new Transaction(5, accountId, Date.valueOf("2024-01-07"), -500.00, description)
        );
        
        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-03"),
            Date.valueOf("2024-01-07")
        )).thenReturn(mockTx);

        Summary ExpectedSummary = new SummaryRangeVO(10000, -2500.0, 7500.0, Date.valueOf("2024-01-03"), Date.valueOf("2024-01-07"));
        Summary actualSummary = service.getAccountSummary(accountId, Date.valueOf("2024-01-03"), Date.valueOf("2024-01-07"));

        assertEquals(ExpectedSummary, actualSummary, "ExpectedSummary: " + ExpectedSummary.toString() + "\nActual: " + actualSummary.toString());
    }
    
    @Test
    void getAccountRangeSummary_oneDay() {
        long accountId = 2L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(new Account(accountId, "Test", description));

        // Mock transactions
        List<Transaction> mockTx = List.of(
            new Transaction(1, accountId, Date.valueOf("2024-01-03"), 5000.0, description),
            new Transaction(2, accountId, Date.valueOf("2024-01-03"), -5000.0, description)
        );
        
        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-03"),
            Date.valueOf("2024-01-03")
        )).thenReturn(mockTx);

        Summary ExpectedSummary = new SummaryRangeVO(5000, -5000.0, 0.0, Date.valueOf("2024-01-03"), Date.valueOf("2024-01-03"));
        Summary actualSummary = service.getAccountSummary(accountId, Date.valueOf("2024-01-03"), Date.valueOf("2024-01-03"));

        assertEquals(ExpectedSummary, actualSummary, "ExpectedSummary: " + ExpectedSummary.toString() + "\nActual: " + actualSummary.toString());
    
    }

    @Test
    void getAccountRangeSummary_noDay() {
        long accountId = 2L;
        String description = "Testing description";

        // Mock account exists
        when(accountRepo.findAccountbyId(accountId))
                .thenReturn(new Account(accountId, "Test", description));

        // Mock transactions
        List<Transaction> mockTx = List.of(
        );

        when(transactionRepo.getAllTransactionsFromAccountInDateRange(
            accountId,
            Date.valueOf("2024-01-01"),
            Date.valueOf("2024-01-01")
        )).thenReturn(mockTx);

        Summary ExpectedSummary = new SummaryRangeVO(0.0, 0.0, 0.0, Date.valueOf("2024-01-01"), Date.valueOf("2024-01-01"));
        Summary actualSummary = service.getAccountSummary(accountId, Date.valueOf("2024-01-01"), Date.valueOf("2024-01-01"));

        assertEquals(ExpectedSummary, actualSummary, "ExpectedSummary: " + ExpectedSummary.toString() + "\nActual: " + actualSummary.toString());
    }
}
