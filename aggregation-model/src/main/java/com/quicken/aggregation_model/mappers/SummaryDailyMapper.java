package com.quicken.aggregation_model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.quicken.aggregation_model.dto.SummaryDailyDto;
import com.quicken.aggregation_model.vo.Summary.Summary;

@Mapper(componentModel = "spring")
public interface SummaryDailyMapper {
    @Mapping(
        target = "date",
        expression = "java(summary.getDateRange().get(0))"
    )
    SummaryDailyDto toDto(Summary summary);
}
