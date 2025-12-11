package com.quicken.aggregation_model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDto {
    long id;
    String name;
    String description;
}
