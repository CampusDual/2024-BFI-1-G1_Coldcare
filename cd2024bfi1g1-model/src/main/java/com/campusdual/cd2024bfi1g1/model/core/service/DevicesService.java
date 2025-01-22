package com.campusdual.cd2024bfi1g1.model.core.service;
import com.campusdual.cd2024bfi1g1.api.core.service.IDevicesService;


import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao.*;

@Service("DevicesService")
@Lazy
public class DevicesService implements IDevicesService {

    @Autowired
    private DevicesDao devicesDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult devicesQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.devicesDao, keyMap, attrList);
    }

    @Override
    public EntityResult devicesWithoutUserQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.devicesDao, keyMap, attrList,"devicesWithoutUser");
    }

    @Override
    public EntityResult devicesInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        attrMap.put(DEV_NAME, attrMap.get(DEV_MAC));
        attrMap.put(DEV_PERSISTENCE, 0);
        return this.daoHelper.insert(this.devicesDao, attrMap);
    }

    @Override
    public EntityResult devicesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.devicesDao, attrMap, keyMap);
    }

    @Override
    public EntityResult devicesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.devicesDao, keyMap);
    }

    @Override
    public EntityResult lastTimeQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Integer userId = Util.getUserId();

        Map<String, Object> filter = new HashMap<>();
        filter.put(UserDao.USR_ID, userId);
        List<String> columns = List.of(
                "CMP_ID"
        );

        final EntityResult userEr = this.daoHelper.query(this.userDao, filter, columns);
        if (userEr.isEmpty()) {
            throw new RuntimeException("Unknown user");
        }

        Map<String, Object> user = userEr.getRecordValues(0);
        Integer cmpId = (Integer) user.get("CMP_ID");

        keyMap.put(DevicesDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.devicesDao, keyMap, attrList, "last_time");
    }

    public EntityResult lastTimeWithoutCMP(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        return this.daoHelper.query(this.devicesDao, keyMap, attrList, "last_time");
    }

}


