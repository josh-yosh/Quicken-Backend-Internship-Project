package com.quicken.aggregation_model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.quicken.aggregation_model.model.Account;
import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AccountRepo {
    
    private final JdbcTemplate jdbc;

    public List<Account> getAllAccounts(){
        String sql = "SELECT * FROM Accounts";
        List<Account> allAccounts = jdbc.query(sql, new AccountRowMapper());;
        return allAccounts;
    }

    private static class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setDescription(rs.getString("description"));
            account.setName(rs.getString("name"));
            return account;
        }
    }


}
