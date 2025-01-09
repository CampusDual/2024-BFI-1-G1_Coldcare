package com.campusdual.cd2024bfi1g1.model.core.service;




import com.campusdual.cd2024bfi1g1.api.core.service.IMeasurementsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.service.DevicesService;
import com.campusdual.cd2024bfi1g1.model.core.dao.MeasurementsDao;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("MeasurementsService")
@Lazy
public class MeasurementsService implements IMeasurementsService {

    @Autowired
    private MeasurementsDao measurementsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private DevicesService devicesService;

    @Override
    @Secured({ PermissionsProviderSecured.SECURED })
    public EntityResult measurementsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query( this.measurementsDao, keyMap, attrList);
    }

    @Override
    @Secured({ PermissionsProviderSecured.SECURED })
    public EntityResult measurementsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> mac = new HashMap<String, Object>();
        mac.put(DevicesDao.DEV_MAC, (String) attrMap.get(DevicesDao.DEV_MAC));
        List<String> attributes = new ArrayList<>();
        attributes.add("DEV_ID");
        attributes.add("DEV_MAC");
        EntityResult query = devicesService.devicesQuery(mac,attributes);
        if(!query.isEmpty() && !query.isWrong()){
            Map<String, Object> row = query.getRecordValues(0);
            attrMap.put(MeasurementsDao.DEV_ID, row.get(DevicesDao.DEV_ID));
            return this.daoHelper.insert( this.measurementsDao, attrMap);
        }else if(query.isEmpty()){
            EntityResult mac_insert = devicesService.devicesInsert(mac);
            attrMap.put(MeasurementsDao.DEV_ID, mac_insert.get(DevicesDao.DEV_ID));
            return this.daoHelper.insert( this.measurementsDao, attrMap);
        }else{
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG,EntityResult.NODATA_RESULT, "No se ha podido realizar la query");
           }

    }

    @Override
    @Secured({ PermissionsProviderSecured.SECURED })
    public EntityResult measurementsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update( this.measurementsDao, attrMap, keyMap);
    }

    @Override
    @Secured({ PermissionsProviderSecured.SECURED })
    public EntityResult measurementsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete( this.measurementsDao, keyMap);
    }

}