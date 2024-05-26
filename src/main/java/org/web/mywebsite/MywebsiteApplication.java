package org.web.mywebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MywebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MywebsiteApplication.class, args);
    }

}
