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
        EntityResult toRet =this.daoHelper.insert(this.pricingDao, attrMap);

        Map<String, Object> date = new HashMap<String, Object>();
        date.put(PricingDao.PP_END, attrMap.get(PricingDao.PP_START));

        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(PricingDao.PP_ID,toRet.getRecordValues(0));
        List<String> column = List.of(PricingDao.PLN_ID);
        EntityResult planId = this.daoHelper.query(this.pricingDao, keyMap, column);

        Map<String, Object> planIdMap = new HashMap<String, Object>();
        planIdMap.put(PricingDao.PLN_ID, planId.getRecordValues(0));

        List<String> filter = List.of(PricingDao.PP_ID);
        EntityResult priceId = this.daoHelper.query(this.pricingDao, planIdMap, filter, "last_prices");
        if(priceId.isEmpty() || priceId.isWrong()) {
            return toRet;
        }
        Map<String, Object> priceMap = new HashMap<String, Object>();
        priceMap.put(PricingDao.PP_ID, priceId.getRecordValues(0));
        this.daoHelper.update(this.pricingDao,date,priceMap);

        return toRet;
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
