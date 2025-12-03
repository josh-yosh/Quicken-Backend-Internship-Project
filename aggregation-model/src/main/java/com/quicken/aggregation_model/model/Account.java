package com.quicken.aggregation_model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Account {
    private long id;
    private String name;
    private String description;
}
