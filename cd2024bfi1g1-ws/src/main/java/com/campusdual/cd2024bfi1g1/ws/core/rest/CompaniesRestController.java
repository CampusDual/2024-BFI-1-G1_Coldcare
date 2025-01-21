package com.campusdual.cd2024bfi1g1.ws.core.rest;

import com.campusdual.cd2024bfi1g1.api.core.service.ICompaniesService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompaniesRestController extends ORestController<ICompaniesService> {
    @Autowired
    private ICompaniesService companiesService;

    @Override
    public ICompaniesService getService() {
        return this.companiesService;
    }
}
