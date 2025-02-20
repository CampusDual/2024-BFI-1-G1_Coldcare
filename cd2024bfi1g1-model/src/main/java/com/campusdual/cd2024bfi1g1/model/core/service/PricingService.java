package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IPricingService;
import com.campusdual.cd2024bfi1g1.model.core.dao.PlanDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.PricingDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.db.AdvancedEntity;
import com.ontimize.jee.common.db.SQLStatementBuilder;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service ("PricingService")
@Lazy
public class PricingService implements IPricingService {

    @Autowired
    private PricingDao pricingDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PlanService planService;
    @Override
    public EntityResult pricingQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.pricingDao, keyMap, attrList);
    }

    @Override
    public EntityResult pricingInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.pricingDao, attrMap);
    }

    @Override
    public EntityResult pricingUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {

        return this.daoHelper.update(this.pricingDao,attrMap,keyMap);
    }

    @Override
    public EntityResult pricingDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {

            return this.daoHelper.delete(this.pricingDao, keyMap);

    }
}
