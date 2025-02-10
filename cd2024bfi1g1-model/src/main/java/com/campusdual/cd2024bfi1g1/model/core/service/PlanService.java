package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IPlanService;
import com.campusdual.cd2024bfi1g1.model.core.dao.PlanDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.PricingDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service ("PlanService")
@Lazy
public class PlanService implements IPlanService {

    @Autowired
    private PlanDao planDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PricingService pricingService;

    @Override
    public EntityResult planQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.planDao, keyMap, attrList);
    }

    @Override
    public EntityResult planInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> plan = new HashMap<String, Object>();
        plan.put(PlanDao.PLN_NAME, attrMap.get(PlanDao.PLN_NAME));
        EntityResult toRet = this.daoHelper.insert(this.planDao, plan);
        Map<String, Object> prices = new HashMap<String, Object>();
        prices.put(PricingDao.PLN_ID, toRet.get(PlanDao.PLN_ID));
        prices.put(PricingDao.PRC_FPRC, attrMap.get(PricingDao.PRC_FPRC));
        prices.put(PricingDao.PRC_DPRC, attrMap.get(PricingDao.PRC_DPRC));
        prices.put(PricingDao.PRC_BPRC, attrMap.get(PricingDao.PRC_BPRC));
        prices.put(PricingDao.PRC_BREQ, attrMap.get(PricingDao.PRC_BREQ));
        prices.put(PricingDao.PRC_START, attrMap.get(PricingDao.PRC_START));
        pricingService.pricingInsert(prices);
        return toRet;
    }

    @Override
    public EntityResult planUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        EntityResult toRet = this.daoHelper.update(this.planDao, attrMap, keyMap);
        Map<String, Object> prices = attrMap;
        prices.remove(PlanDao.PLN_NAME);
        EntityResult noNameRet = pricingService.pricingUpdate(prices, keyMap);
        if(attrMap.containsKey(PlanDao.PLN_NAME)){
            return toRet;
        }else{
            return noNameRet;
        }
    }

    @Override
    public EntityResult planDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.planDao, keyMap);
    }


}