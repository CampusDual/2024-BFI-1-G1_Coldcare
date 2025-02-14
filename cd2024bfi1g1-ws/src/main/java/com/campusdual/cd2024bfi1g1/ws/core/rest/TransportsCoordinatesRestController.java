package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransportsCoordinatesService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transportsCoordinates")
public class TransportsCoordinatesRestController extends ORestController<ITransportsCoordinatesService> {
    @Autowired
    private ITransportsCoordinatesService transportsCoordinatesService;

    @Override
    public ITransportsCoordinatesService getService() {
        return this.transportsCoordinatesService;
    }
}
