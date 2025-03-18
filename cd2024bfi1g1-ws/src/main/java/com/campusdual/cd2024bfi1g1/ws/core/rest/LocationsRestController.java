package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ILocationsService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationsRestController extends ORestController<ILocationsService> {
    @Autowired
    private ILocationsService locationsService;

    @Override
    public ILocationsService getService() {
        return this.locationsService;
    }
}
