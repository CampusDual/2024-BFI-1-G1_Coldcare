package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IAlertsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.AlertsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("AlertsService")
@Lazy

public class AlertsService implements IAlertsService {
    @Autowired
    private AlertsDao alertsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;


    @Override
    public EntityResult alertsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(DevicesDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.alertsDao, keyMap, attrList);
    }

    @Override
    public EntityResult containersAlertsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(DevicesDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.alertsDao, keyMap, attrList, "containersAlerts");
    }

    @Override
    public EntityResult alertsWithCalculatedColumnsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(DevicesDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.alertsDao, keyMap, attrList, "alertsWithCalculatedColumns");
    }


    @Override
    public AdvancedEntityResult alertsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        ((Map<String,Object>)keysValues).put(DevicesDao.CMP_ID, cmpId);
        return this.daoHelper.paginationQuery(this.alertsDao,  keysValues, attributes, pagesize, offset, orderBy, "alertsWithContainersLots");
    }

    @Override
    public EntityResult alertsInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {

        return this.daoHelper.insert(this.alertsDao, attrMap);
    }

    @Override
    public EntityResult alertsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {

        return this.daoHelper.update(this.alertsDao, attrMap, keyMap);
    }
    @Override
    public EntityResult  alertsWithCalculatedColumnsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.alertsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult alertsDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.alertsDao, keyMap);
    }
}
