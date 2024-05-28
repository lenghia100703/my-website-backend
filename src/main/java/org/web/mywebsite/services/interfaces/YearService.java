package org.web.mywebsite.services.interfaces;

import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.year.YearDto;

public interface YearService {
    void createNextYear();

    PaginatedDataDto<YearDto> getYearByPage(int page);

    CommonResponseDto<YearDto> getCurrentYear();

    CommonResponseDto<YearDto> getYearById(Long id);
}
