package org.web.mywebsite.configs.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web.mywebsite.services.interfaces.SemesterService;

@Component
public class SemesterScheduler {
    @Autowired
    private SemesterService semesterService;

    @Scheduled(cron = "0 0 0 * * ?")  // Chạy vào nửa đêm mỗi ngày
    public void checkAndCreateNextSemester() {
        semesterService.createNextSemester();
    }
}
