package com.quicken.aggregation_model.mappers;

import org.mapstruct.Mapper;

import com.quicken.aggregation_model.dto.SummaryDailyDto;
import com.quicken.aggregation_model.vo.Summary.SummaryDailyVO;

@Mapper(componentModel = "spring")
public interface SummaryDailyMapper {
    SummaryDailyDto toDto(SummaryDailyVO summary);
}
