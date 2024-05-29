package org.web.mywebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.mywebsite.repositories.SemesterRepository;
import org.web.mywebsite.repositories.YearRepository;
import org.web.mywebsite.services.interfaces.SemesterService;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    YearRepository yearRepository;

    @Override
    public void createNextSemester() {

    }
}
