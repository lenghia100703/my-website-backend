package org.web.mywebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.mywebsite.entities.SemesterEntity;
import org.web.mywebsite.repositories.SemesterRepository;
import org.web.mywebsite.services.interfaces.SemesterService;

import java.time.LocalDate;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    SemesterRepository semesterRepository;

    @Override
    public void createNextSemester() {
        SemesterEntity lastSemester = semesterRepository.findFirstByOrderByEndDateDesc().orElse(null);

        if (lastSemester != null && lastSemester.getEndDate().isBefore(LocalDate.now())) {
            LocalDate nextStartDate;
            LocalDate nextEndDate;
            String nextSemesterName;

            if (lastSemester.getEndDate().getMonthValue() == 12) {
                nextStartDate = LocalDate.of(lastSemester.getEndDate().getYear() + 1, 1, 15);
                nextEndDate = LocalDate.of(lastSemester.getEndDate().getYear() + 1, 6, 30);
                nextSemesterName = "Kì 1 " + (lastSemester.getEndDate().getYear() + 1);
            } else {
                nextStartDate = LocalDate.of(lastSemester.getEndDate().getYear(), 8, 1);
                nextEndDate = LocalDate.of(lastSemester.getEndDate().getYear(), 12, 31);
                nextSemesterName = "Kì 2 " + lastSemester.getEndDate().getYear();
            }

            SemesterEntity nextSemester = new SemesterEntity();
            nextSemester.setName(nextSemesterName);
            nextSemester.setStartDate(nextStartDate);
            nextSemester.setEndDate(nextEndDate);
            nextSemester.setIsActive(true);
            lastSemester.setIsActive(false);

            semesterRepository.save(lastSemester);
            semesterRepository.save(nextSemester);
        }
    }
}
