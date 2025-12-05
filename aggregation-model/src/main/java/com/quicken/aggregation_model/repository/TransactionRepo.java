package com.quicken.aggregation_model.repository;

import java.sql.Date;
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

    public List<Transaction> getAllTransactionsFromAccountInDateRange(long id, Date startDate, Date endDate){
        String sql = "SELECT * FROM transactions WHERE account_id = ? AND date BETWEEN ? AND ?";
        Object[] args = {id, startDate, endDate};
        List<Transaction> allAccountTransactionsInRange = jdbc.query(sql, ROW_MAPPER, args);
        
        return allAccountTransactionsInRange;
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