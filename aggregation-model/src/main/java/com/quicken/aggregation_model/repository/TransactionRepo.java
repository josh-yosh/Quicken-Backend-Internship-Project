package com.quicken.aggregation_model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.quicken.aggregation_model.model.Account;
import com.quicken.aggregation_model.model.Transaction;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TransactionRepo {
    private final JdbcTemplate jdbc;
    private static final TransactionRowMapper ROW_MAPPER =  new TransactionRowMapper();

    public List<Transaction> getAllTransactionsFromAccount(long id){
        String sql = "SELECT * FROM transactions WHERE account_id = ?";
        //debated on creating an Object[] args to pass to query
        List<Transaction> allAccountTransactions = jdbc.query(sql, ROW_MAPPER, id);
        
        return allAccountTransactions;
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setAccountId(rs.getLong("account_id"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setDate(rs.getDate("date"));
            return transaction;
        }
    }

}