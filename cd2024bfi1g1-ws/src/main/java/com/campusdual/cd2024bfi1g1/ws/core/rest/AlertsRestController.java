package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.IAlertsService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertsRestController extends ORestController<IAlertsService> {
    @Autowired
    private IAlertsService alertsService;

    @Override
    public IAlertsService getService() {
        return this.alertsService;
    }
}
