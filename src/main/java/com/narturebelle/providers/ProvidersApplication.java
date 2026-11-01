package com.narturebelle.providers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class ProvidersApplication {

    public static void main(String[] args) {
        // Slots and licence dates are local India times; containers usually run in UTC.
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplication.run(ProvidersApplication.class, args);
    }
}
