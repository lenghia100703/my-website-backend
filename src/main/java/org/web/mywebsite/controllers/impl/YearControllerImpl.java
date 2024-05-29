package org.web.mywebsite.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.web.mywebsite.controllers.interfaces.YearController;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.year.YearDto;
import org.web.mywebsite.services.interfaces.YearService;

@RestController
public class YearControllerImpl implements YearController {
    @Autowired
    YearService yearService;

    @Override
    public CommonResponseDto<YearDto> getCurrentYear() {
        return yearService.getCurrentYear();
    }

    @Override
    public CommonResponseDto<YearDto> getYearById(Long id) {
        return yearService.getYearById(id);
    }

    @Override
    public PaginatedDataDto<YearDto> getYearByPage(int page) {
        return yearService.getYearByPage(page);
    }
}
