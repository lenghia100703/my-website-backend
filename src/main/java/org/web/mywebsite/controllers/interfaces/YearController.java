package org.web.mywebsite.controllers.interfaces;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;
import org.web.mywebsite.dtos.auth.AuthResponseDto;
import org.web.mywebsite.dtos.auth.LoginDto;
import org.web.mywebsite.dtos.auth.SignUpDto;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.user.UserDto;
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
