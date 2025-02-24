package com.campusdual.cd2024bfi1g1.ws.core.rest;


import com.campusdual.cd2024bfi1g1.api.core.service.IPlanService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/plan")
public class PlanRestController extends ORestController<IPlanService> {

    @Autowired
    private IPlanService planService;

    @Override
    public IPlanService getService() {
        return this.planService;
    }

}
