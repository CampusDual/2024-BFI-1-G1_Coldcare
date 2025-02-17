package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransportersService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transporters")
public class TransportersRestController extends ORestController<ITransportersService> {
    @Autowired
    private ITransportersService transportersService;

    @Override
    public ITransportersService getService() {
        return this.transportersService;
    }
}
