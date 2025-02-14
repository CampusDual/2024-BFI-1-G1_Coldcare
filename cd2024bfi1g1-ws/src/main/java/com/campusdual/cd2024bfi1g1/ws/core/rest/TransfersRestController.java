package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransfersService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransfersRestController extends ORestController<ITransfersService> {

    @Autowired
    private ITransfersService transfersService;

    @Override
    public ITransfersService getService() {
        return this.transfersService;
    }

}