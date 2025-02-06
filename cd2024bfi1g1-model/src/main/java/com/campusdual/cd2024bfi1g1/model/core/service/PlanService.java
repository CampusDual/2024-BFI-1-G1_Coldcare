package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IPlanService;
import com.campusdual.cd2024bfi1g1.model.core.dao.PlanDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.text.Keymap;
import java.util.*;

import static com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao.CNT_ID;

@Service ("PlanService")
@Lazy
public class PlanService implements IPlanService {

    @Autowired
    private PlanDao planDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;

    @Override
    public EntityResult planQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.planDao, keyMap, attrList);
    }

    @Override
    public EntityResult planInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.planDao, attrMap);
    }

    @Override
    public EntityResult planUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        Object plnId = keyMap.get(PlanDao.PLN_ID);

            if (isDateOK(attrMap,keyMap)){
                return this.daoHelper.update(this.planDao, attrMap, keyMap);
            }
            else {
                throw new OntimizeJEERuntimeException("'min_temp' no puede ser mayor que 'max_temp'.");
            }


    }

    @Override
    public EntityResult planDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.planDao, keyMap);
    }

    private boolean isDateOK(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {

        if (attrMap.containsKey(PlanDao.PLN_START) && attrMap.containsKey(PlanDao.PLN_END)) {
            if (((Date) attrMap.get(PlanDao.PLN_START)).after ((Date) attrMap.get(PlanDao.PLN_END))) {
                return false;
            }
            else {
                return true;
            }


        }
        else if (attrMap.containsKey(PlanDao.PLN_START)) {

            List<String> attrList = new ArrayList<>();
            attrList.add(PlanDao.PLN_END);

            EntityResult result = this.daoHelper.query(this.planDao, keyMap, attrList);
            if ( result.get(PlanDao.PLN_END) == null || result.get(PlanDao.PLN_END).toString().isEmpty()) {

                return true;
            }
            else {
                if (((Date) attrMap.get(PlanDao.PLN_START)).after ((Date)result.get(PlanDao.PLN_END) )) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        else if (attrMap.containsKey(PlanDao.PLN_END))
        {

            List<String> attrList = new ArrayList<>();
            attrList.add(PlanDao.PLN_START);

            EntityResult result = this.daoHelper.query(this.planDao, keyMap, attrList);
            if (((Date) attrMap.get(PlanDao.PLN_END)).before ((Date)result.get(PlanDao.PLN_START) )) {
                return false;
            }
            else {
                return true;
            }
        }
        return true;
    }
}