package com.campusdual.cd2024bfi1g1.model.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BillsScheduler {

    @Autowired
    private BillsService billsService;

    //@Scheduled(cron = "*/50 * * * * *") //cada 50 segundos
    @Scheduled(cron = "0 0 */2 * * *") //cada 2 horas
    public void createOrUpdateBills() {

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        int pastMonthYear = LocalDate.now().minusMonths(1).getYear();
        int pastMonth = LocalDate.now().minusMonths(1).getMonthValue();

        this.billsService.modifyData(pastMonthYear, pastMonth);
        this.billsService.modifyData(year, month);
    }
}
