package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.IUserFirebaseTokenService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userFirebaseToken")
public class UserFirebaseTokenRestController extends ORestController<IUserFirebaseTokenService> {

    @Autowired
    private IUserFirebaseTokenService userFirebaseTokenService;

    @Override
    public IUserFirebaseTokenService getService() {
        return this.userFirebaseTokenService;
    }

}