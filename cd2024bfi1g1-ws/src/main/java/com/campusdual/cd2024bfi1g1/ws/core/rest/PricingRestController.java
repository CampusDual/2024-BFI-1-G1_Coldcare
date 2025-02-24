package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.IPricingService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pricing")

public class PricingRestController extends ORestController<IPricingService> {
    @Autowired
    private IPricingService pricingService;

    @Override
    public IPricingService getService() {
        return this.pricingService;
    }
}
