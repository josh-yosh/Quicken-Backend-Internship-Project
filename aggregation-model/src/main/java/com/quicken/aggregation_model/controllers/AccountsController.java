package com.quicken.aggregation_model.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quicken.aggregation_model.dto.AccountDto;
import com.quicken.aggregation_model.mappers.AccountMapper;
import com.quicken.aggregation_model.service.AggregationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountsController {

    private final AggregationService aggregationService;
    private final AccountMapper accountMapper;

    @GetMapping
    public List<AccountDto> getAccounts(){
        return aggregationService.getAllAccounts()
                                    .stream()
                                    .map(accountMapper::toDto)
                                    .toList();
    }

    @GetMapping("/{accountId}/summary?from=YYYY-MM-DD&to=YYYY-MM-DD")
    public SummaryRangeDto getAccountSummaryRange(){
        return null;
    }
}
