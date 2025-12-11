package com.quicken.aggregation_model.mappers;

import org.mapstruct.Mapper;

import com.quicken.aggregation_model.dto.AccountDto;
import com.quicken.aggregation_model.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
}
