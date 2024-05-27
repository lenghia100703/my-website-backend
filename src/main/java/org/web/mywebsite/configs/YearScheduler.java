package org.web.mywebsite.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web.mywebsite.services.interfaces.YearService;

@Component
public class YearScheduler {

    @Autowired
    private YearService yearService;

    @Scheduled(cron = "0 0 0 * * ?")  // Chạy vào nửa đêm mỗi ngày
    public void checkAndCreateNextYear() {
        yearService.createNextYear();
    }
}
