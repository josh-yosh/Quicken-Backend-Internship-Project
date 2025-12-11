package com.quicken.aggregation_model.mappers;

import org.mapstruct.Mapper;

import com.quicken.aggregation_model.dto.SummaryRangeDto;
import com.quicken.aggregation_model.vo.Summary.SummaryRangeVO;

@Mapper(componentModel = "spring")
public interface SummaryRangeMapper {
    SummaryRangeDto toDto(SummaryRangeVO summary);
}
