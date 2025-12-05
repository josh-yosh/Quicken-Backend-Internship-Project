package com.quicken.aggregation_model.repoTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import com.quicken.aggregation_model.model.Transaction;
import com.quicken.aggregation_model.repository.TransactionRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Import(TransactionRepo.class) 
@Sql("/quicken_project.sql")
public class TransactionRepoTests {
    @Autowired
    private TransactionRepo transactionRepo;

    @Test
    void getAllAccounts_returnsAllRowsFromDatabase() {
        Date startDate = Date.valueOf("2024-01-01");
        Date endDate = Date.valueOf("2024-01-10");
        List<Transaction> transactions = transactionRepo.getAllTransactionsFromAccountInDateRange(1L, startDate, endDate);

        assertThat(transactions).hasSize(5);

        //  (1001,1,'2024-01-01',3500.00,'Monthly salary'),
        Transaction first = transactions.get(0);
        assertThat(first.getId()).isEqualTo(1001L);
        assertThat(first.getAccountId()).isEqualTo(1L);
        assertThat(first.getAmount()).isEqualTo(3500.00);
        assertThat(first.getDate()).isEqualTo("2024-01-01");
        assertThat(first.getDescription()).isEqualTo("Monthly salary");
    }

}
