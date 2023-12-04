package com.dev.alex.phonecollect.utils;

import com.dev.alex.phonecollect.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Lazy(false)
public class PhoneCollectorScheduler {

    @Autowired
    PhoneService phoneService;

    @Scheduled(cron = "0 0 * * * *")
    private void collectAllNumbers() {
        System.out.println("Start collect");
        phoneService.collectAllNumbers();
        System.out.println("All numbers collected");
    }

    @Scheduled(cron = "0 0 2 ? * MON")
    private void deleteOldPhones() {
        phoneService.collectAllNumbers();
        System.out.println("Old Phones deleted");
    }
}
