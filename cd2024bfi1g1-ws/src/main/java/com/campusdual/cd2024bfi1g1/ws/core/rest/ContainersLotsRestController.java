package com.campusdual.cd2024bfi1g1.ws.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusdual.cd2024bfi1g1.api.core.service.IContainersLotsService;
import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/containersLots")
public class ContainersLotsRestController extends ORestController<IContainersLotsService> {

    @Autowired
    private IContainersLotsService containersLotsService;

    @Override
    public IContainersLotsService getService() {
        return this.containersLotsService;
    }
}
