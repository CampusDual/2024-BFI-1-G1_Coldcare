package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransportsService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transports")
public class TransportsRestController extends ORestController<ITransportsService> {

    @Autowired
    private ITransportsService transportsService;

    @Override
    public ITransportsService getService() {
        return this.transportsService;
    }

}