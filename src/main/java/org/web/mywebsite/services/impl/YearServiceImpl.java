package org.web.mywebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.web.mywebsite.constants.PageableConstants;
import org.web.mywebsite.dtos.common.CommonResponseDto;
import org.web.mywebsite.dtos.common.PaginatedDataDto;
import org.web.mywebsite.dtos.year.YearDto;
import org.web.mywebsite.entities.SemesterEntity;
import org.web.mywebsite.entities.YearEntity;
import org.web.mywebsite.enums.ResponseCode;
import org.web.mywebsite.exceptions.CommonException;
import org.web.mywebsite.repositories.SemesterRepository;
import org.web.mywebsite.repositories.YearRepository;
import org.web.mywebsite.services.interfaces.YearService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class YearServiceImpl implements YearService {
    @Autowired
    YearRepository yearRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Override
    public void createNextYear() {
        YearEntity lastYear = yearRepository.findFirstByOrderByEndDateDesc().orElse(null);

        if (lastYear != null && lastYear.getEndDate().isBefore(LocalDate.now())) {
            LocalDate nextStartDate = LocalDate.of(lastYear.getEndDate().getYear(), 8, 1);
            LocalDate nextEndDate = LocalDate.of(lastYear.getEndDate().getYear() + 1, 6, 30);

            YearEntity nextYear = new YearEntity();
            nextYear.setTitle(nextStartDate.getYear() + "-" + nextEndDate.getYear());
            nextYear.setStartDate(nextStartDate);
            nextYear.setEndDate(nextEndDate);
            nextYear.setIsActive(true);
            nextYear.setCreatedAt(new Date(System.currentTimeMillis()));

            lastYear.setIsActive(false);
            yearRepository.save(lastYear);
            yearRepository.save(nextYear);

            List<SemesterEntity> semesters = new ArrayList<>();

            SemesterEntity semester1 = new SemesterEntity();
            semester1.setName("Kì 1 " + nextStartDate.getYear() + "-" + nextEndDate.getYear());
            semester1.setDescription("Học kì 1 năm học " + nextStartDate.getYear() + "-" + nextEndDate.getYear());
            semester1.setStartDate(nextStartDate);
            semester1.setEndDate(LocalDate.of(nextStartDate.getYear(), 12, 31));
            semester1.setIsActive(false);
            semester1.setYear(nextYear);
            semester1.setCreatedAt(new Date(System.currentTimeMillis()));
            semesterRepository.save(semester1);
            semesters.add(semester1);

            SemesterEntity semester2 = new SemesterEntity();
            semester2.setName("Kì 2 " + nextStartDate.getYear() + "-" + nextEndDate.getYear());
            semester1.setDescription("Học kì 2 năm học " + nextStartDate.getYear() + "-" + nextEndDate.getYear());
            semester2.setStartDate(LocalDate.of(nextStartDate.getYear() + 1, 1, 15));
            semester2.setEndDate(nextEndDate);
            semester2.setIsActive(false);
            semester2.setYear(nextYear);
            semester2.setCreatedAt(new Date(System.currentTimeMillis()));
            semesterRepository.save(semester2);
            semesters.add(semester2);

            nextYear.setSemesters(semesters);
            yearRepository.save(nextYear);
        }
    }

    @Override
    public PaginatedDataDto<YearDto> getYearByPage(int page) {
        List<YearEntity> years = yearRepository.findAll();
        if (page >= 1) {
            Pageable pageable = PageRequest.of(page - 1, PageableConstants.LIMIT);
            Page<YearEntity> yearByPage = yearRepository.findAll(pageable);

            List<YearEntity> news = yearByPage.getContent();
            return new PaginatedDataDto<>(news.stream().map(YearDto::new).toList(), page, years.toArray().length);

        } else {
            return new PaginatedDataDto<>(years.stream().map(YearDto::new).toList(), 1, years.toArray().length);
        }
    }

    @Override
    public CommonResponseDto<YearDto> getCurrentYear() {
        YearEntity year = yearRepository.findCurrentYear(LocalDate.now()).orElseThrow();

        return new CommonResponseDto<>(new YearDto(year));
    }

    @Override
    public CommonResponseDto<YearDto> getYearById(Long id) {
        return new CommonResponseDto<>(new YearDto(yearRepository.getById(id)));
    }
}
