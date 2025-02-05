package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.ICompaniesService;
import com.campusdual.cd2024bfi1g1.api.core.service.IProductService;
import com.campusdual.cd2024bfi1g1.model.core.dao.CompaniesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ProductService")
@Lazy
public class ProductService implements IProductService {
    @Autowired
    private CompaniesDao productDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult productQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.productDao, keyMap, attrList);
    }

    @Override
    public EntityResult productInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.productDao, attrMap);
    }

    @Override
    public EntityResult productUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.productDao, attrMap, keyMap);
    }

    @Override
    public EntityResult productDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.productDao, keyMap);
    }
}
