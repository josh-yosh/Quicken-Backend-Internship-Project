package com.quicken.aggregation_model.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quicken.aggregation_model.dto.AccountDto;
import com.quicken.aggregation_model.dto.SummaryRangeDto;
import com.quicken.aggregation_model.mappers.AccountMapper;
import com.quicken.aggregation_model.mappers.SummaryRangeMapper;
import com.quicken.aggregation_model.service.AggregationService;
import com.quicken.aggregation_model.vo.Summary.SummaryRangeVO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountsController {

    private final AggregationService aggregationService;
    private final AccountMapper accountMapper;
    private final SummaryRangeMapper summaryRangeMapper;

    @GetMapping
    public List<AccountDto> getAccounts(){
        return aggregationService.getAllAccounts()
                                    .stream()
                                    .map(accountMapper::toDto)
                                    .toList();
    }

    @GetMapping("/{accountId}/summary")
    public SummaryRangeDto getAccountSummaryRange(@PathVariable long accountId,
                                               @RequestParam(name = "from") String startDate,
                                                 @RequestParam(name = "to") String endDate)
    {
        SummaryRangeVO summaryRange = aggregationService.getAccountSummary(accountId, Date.valueOf(startDate), Date.valueOf(endDate));
        return summaryRangeMapper.toDto(summaryRange);
    }
}
