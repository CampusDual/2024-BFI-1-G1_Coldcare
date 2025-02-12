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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> conditions = new HashMap<>(keyMap);
        conditions.put(
                SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY,
                Util.isDateInCurrentRange(PricingDao.PRC_START, PricingDao.PRC_END)
        );
        EntityResult result = this.daoHelper.query(this.pricingDao, conditions, attrList);
        return result;
    }

    @Override
    public EntityResult pricingInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

        return this.daoHelper.insert(this.pricingDao, attrMap);
    }

    @Override
    public EntityResult pricingUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        if(!attrMap.containsKey(PlanDao.PLN_NAME)){
            Map<String, Object> ending = new HashMap<>();
            ending.put(PricingDao.PRC_END, LocalDateTime.now());
            this.daoHelper.update(this.pricingDao, ending, keyMap);

            List<String> columns = List.of(
                    PricingDao.PRC_FPRC, PricingDao.PRC_DPRC, PricingDao.PRC_BPRC, PricingDao.PRC_BREQ, PricingDao.PRC_START
            );
            EntityResult result = this.daoHelper.query(this.pricingDao, keyMap, columns);

            if (result.calculateRecordNumber() == 0) {
                throw new OntimizeJEERuntimeException("No se encontró el pricing para actualizar.");
            }
            Map<String, Object> previousPricing = result.getRecordValues(0);
            Map<String, Object> newPricing = new HashMap<>(previousPricing);
            newPricing.putAll(attrMap); // Reemplaza solo los valores modificados
            newPricing.put(PricingDao.PRC_START, LocalDateTime.now()); // Nueva fecha de inicio

            // Insertar el nuevo pricing
            return this.pricingInsert(newPricing);
            /*Solo registra la fecha final a la fecha de hoy, al llamar al metodo insert, no obtiene el PLN_ID y no
            genera el nuevo pricing*/
           //return this.daoHelper.update(this.pricingDao, attrMap, keyMap);


        }else if(attrMap.size()==1){
            return planService.planUpdate(attrMap, keyMap);
        }else{
            Map<String, Object> name = new HashMap<String, Object>();
            name.put(PlanDao.PLN_NAME, attrMap.get(PlanDao.PLN_NAME));
            planService.planUpdate(name, keyMap);
            Map<String, Object> prices = attrMap;
            prices.remove(PlanDao.PLN_NAME);
            return this.daoHelper.update(this.pricingDao, prices, keyMap);
        }

    }

    @Override
    public EntityResult pricingDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {

            return this.daoHelper.delete(this.pricingDao, keyMap);

    }
}
