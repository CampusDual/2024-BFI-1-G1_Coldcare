package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.ICompaniesService;
import com.campusdual.cd2024bfi1g1.model.core.dao.CompaniesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("CompaniesService")
@Lazy
public class CompaniesService implements ICompaniesService {
    @Autowired
    private CompaniesDao companiesDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult companiesQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.companiesDao, keyMap, attrList);
    }

    @Override
    public EntityResult companiesInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return null;
    }

    @Override
    public EntityResult companiesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return null;
    }

    @Override
    public EntityResult companiesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return null;
    }
}
