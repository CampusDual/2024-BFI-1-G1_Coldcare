package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.IContainersService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/containers")
public class ContainersRestController extends ORestController<IContainersService> {

    @Autowired
    private IContainersService containersService;

    @Override
    public IContainersService getService() {
        return this.containersService;
    }

}
