package org.web.mywebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.mywebsite.entities.SemesterEntity;
import org.web.mywebsite.entities.YearEntity;
import org.web.mywebsite.repositories.YearRepository;
import org.web.mywebsite.services.interfaces.YearService;

import java.time.LocalDate;

@Service
public class YearServiceImpl implements YearService {
    @Autowired
    YearRepository yearRepository;

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
            lastYear.setIsActive(false);

            yearRepository.save(lastYear);
            yearRepository.save(nextYear);
        }
    }
}
