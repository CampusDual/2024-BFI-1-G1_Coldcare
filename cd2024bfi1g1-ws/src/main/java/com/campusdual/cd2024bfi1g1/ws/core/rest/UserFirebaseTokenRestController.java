package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.IUserFirebaseTokenService;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserFirebaseTokenDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userFirebaseToken")
public class UserFirebaseTokenRestController extends ORestController<IUserFirebaseTokenService> {

    @Autowired
    private IUserFirebaseTokenService userFirebaseTokenService;

    @Override
    public IUserFirebaseTokenService getService() {
        return this.userFirebaseTokenService;
    }

    @RequestMapping("/test")
    public ResponseEntity<EntityResult> testsRest() throws Exception {
        return new ResponseEntity<>(this.userFirebaseTokenService.test(), HttpStatus.OK);
    }

}

