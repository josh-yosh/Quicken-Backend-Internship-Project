package com.quicken.aggregation_model.repoTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.repository.AccountRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import(AccountRepo.class) 
@Sql("/quicken_project.sql")
public class AccountRepoTests {

    @Autowired
    private AccountRepo accountRepo;

    @Test
    void getAllAccounts_returnsAllRowsFromDatabase() {
        List<Account> accounts = accountRepo.findAll();

        assertThat(accounts).hasSize(3);

        //  (1, 'Personal Finances 2024', 'Personal income and household expenses'),
        Account first = accounts.get(0);
        assertThat(first.getId()).isEqualTo(1L);
        assertThat(first.getName()).isEqualTo("Personal Finances 2024");
        assertThat(first.getDescription()).isEqualTo("Personal income and household expenses");
    }

    @Test
    void findAccountbyId_returnsSpecifiedAccountfromDB() {
        Account account = accountRepo.findAccountbyId(2L);

        //(2, 'Small Business 2024', 'Simple small business cashflow'),
        assertThat(account.getId()).isEqualTo(2L);
        assertThat(account.getName()).isEqualTo("Small Business 2024");
        assertThat(account.getDescription()).isEqualTo("Simple small business cashflow");
    }

    @Test
    void findAccountbyId_noAccountFoundReturnsNull() {
        assertThrows(RuntimeException.class, () -> {
            Account account = accountRepo.findAccountbyId(2L);
        });
    }
}


