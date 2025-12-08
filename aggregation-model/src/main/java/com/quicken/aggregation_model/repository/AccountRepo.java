package com.quicken.aggregation_model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quicken.aggregation_model.exceptions.AccountNotFoundException;
import com.quicken.aggregation_model.model.Account;
import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AccountRepo {
    
    private final JdbcTemplate jdbc;
    private static final AccountRowMapper ROW_MAPPER =  new AccountRowMapper();

    public List<Account> findAll(){
        String sql = "SELECT * FROM accounts";
        List<Account> allAccounts = jdbc.query(sql, ROW_MAPPER);;
        return allAccounts;
    }

    public Account findAccountbyId(Long id){
        try{
            String sql = "SELECT * FROM accounts WHERE id = ?";
            Account account = jdbc.queryForObject(sql, ROW_MAPPER, id);
            return account;
        }  catch (EmptyResultDataAccessException e) {
            throw new AccountNotFoundException(id);
        }
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
