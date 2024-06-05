package com.witch.pan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Yuuki
 */
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.witch.common", "com.witch.pan"})
public class WitchPanApplication {
    public static void main(String[] args) {
        SpringApplication.run(WitchPanApplication.class, args);
    }
}
