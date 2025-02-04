package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BillsScheduler {

    @Autowired
    private BillsService billsService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void generateMonthlyBills() {

        this.billsService.createBills();
    }
}
