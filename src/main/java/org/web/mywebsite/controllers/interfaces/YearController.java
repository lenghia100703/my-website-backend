package org.web.mywebsite.controllers.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.year.YearDto;

@RequestMapping("/api/year")
public interface YearController {
    @GetMapping("/current-year")
    CommonResponseDto<YearDto> getCurrentYear();

    @GetMapping("/{id}")
    CommonResponseDto<YearDto> getYearById(@PathVariable("id") Long id);

    @GetMapping("")
    PaginatedDataDto<YearDto> getYearByPage(@RequestParam(name = "page") int page);
}
