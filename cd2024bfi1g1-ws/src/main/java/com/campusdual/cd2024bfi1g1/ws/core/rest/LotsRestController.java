package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ILotsService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lots")
public class LotsRestController extends ORestController<ILotsService> {

    @Autowired
    private ILotsService lotsService;

    @Override
    public ILotsService getService() {
        return this.lotsService;
    }

}
