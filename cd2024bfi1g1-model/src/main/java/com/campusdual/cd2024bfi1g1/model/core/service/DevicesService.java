package com.campusdual.cd2024bfi1g1.model.core.service;
import com.campusdual.cd2024bfi1g1.api.core.service.IDevicesService;


import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao.DEV_MAC;
import static com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao.DEV_NAME;
import static com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao.USR_ID;

@Service("DevicesService")
@Lazy
public class DevicesService implements IDevicesService {

    @Autowired
    private DevicesDao DevicesDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult devicesQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.DevicesDao, keyMap, attrList);
    }

    @Override
    public EntityResult devicesWithoutUserQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.DevicesDao, keyMap, attrList,"devicesWithoutUser");
    }

    @Override
    public EntityResult devicesInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        attrMap.put(DEV_NAME, attrMap.get(DEV_MAC));
        attrMap.put(USR_ID, attrMap.get(USR_ID));
        return this.daoHelper.insert(this.DevicesDao, attrMap);
    }

    @Override
    public EntityResult devicesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.DevicesDao, attrMap, keyMap);
    }

    @Override
    public EntityResult devicesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.DevicesDao, keyMap);
    }


}