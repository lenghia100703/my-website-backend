package org.web.mywebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.mywebsite.entities.SemesterEntity;
import org.web.mywebsite.entities.YearEntity;
import org.web.mywebsite.repositories.SemesterRepository;
import org.web.mywebsite.repositories.YearRepository;
import org.web.mywebsite.services.interfaces.YearService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

            lastYear.setIsActive(false);
            yearRepository.save(lastYear);

            List<SemesterEntity> semesters = new ArrayList<>();

            SemesterEntity semester1 = new SemesterEntity();
            semester1.setName("Kì 1 " + nextStartDate.getYear() + "-" + nextEndDate.getYear());
            semester1.setStartDate(nextStartDate);
            semester1.setEndDate(LocalDate.of(nextStartDate.getYear(), 12, 31));
            semester1.setIsActive(false);
            semester1.setYear(nextYear);
            semesterRepository.save(semester1);
            semesters.add(semester1);

            SemesterEntity semester2 = new SemesterEntity();
            semester2.setName("Kì 2 " + nextStartDate.getYear() + "-" + nextEndDate.getYear());
            semester2.setStartDate(LocalDate.of(nextStartDate.getYear() + 1, 1, 15));
            semester2.setEndDate(nextEndDate);
            semester2.setIsActive(false);
            semester2.setYear(nextYear);
            semesterRepository.save(semester2);
            semesters.add(semester2);

            nextYear.setSemesters(semesters);
            yearRepository.save(nextYear);
        }
    }
}
