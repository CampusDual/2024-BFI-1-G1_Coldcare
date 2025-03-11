package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IUserFirebaseTokenService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserFirebaseTokenDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("UserFirebaseTokenService")
@Lazy
public class UserFirebaseTokenService implements IUserFirebaseTokenService {
    @Autowired
    private UserFirebaseTokenDao userFirebaseTokenDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;


    @Override
    public EntityResult userFirebaseTokenQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        return this.daoHelper.query(this.userFirebaseTokenDao, keyMap, attrList);
    }

    @Override
    public EntityResult userFirebaseTokenInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Integer userId = Util.getUserId();
        String token = (String) attrMap.get(UserFirebaseTokenDao.UFT_TOKEN);
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);


        Map<String, Object> filter = new HashMap<>();
        filter.put(UserFirebaseTokenDao.UFT_TOKEN, token);

        List<String> columns = Arrays.asList(UserFirebaseTokenDao.UFT_ID, UserFirebaseTokenDao.USR_ID);
        EntityResult existingResult = this.daoHelper.query(this.userFirebaseTokenDao, filter, columns);

        if (existingResult.calculateRecordNumber() > 0) {
            Integer existingUserId = (Integer) existingResult.getRecordValues(0).get(UserFirebaseTokenDao.USR_ID);
            Integer uftId = (Integer) existingResult.getRecordValues(0).get(UserFirebaseTokenDao.UFT_ID);

            if (existingUserId.equals(userId)) {
                System.out.println("Ya existe este usuario con el mismo token");
                return null;

            } else {
                Map<String, Object> updateValues = new HashMap<>();
                updateValues.put(UserFirebaseTokenDao.USR_ID, userId);

                Map<String, Object> updateFilter = new HashMap<>();
                updateFilter.put(UserFirebaseTokenDao.UFT_ID, uftId);

                return userFirebaseTokenUpdate(updateValues, updateFilter );
            }
        }

        attrMap.put(UserFirebaseTokenDao.USR_ID, userId);
        attrMap.put(DevicesDao.CMP_ID, cmpId);
        return this.daoHelper.insert(this.userFirebaseTokenDao, attrMap);
    }

    @Override
    public EntityResult userFirebaseTokenUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.userFirebaseTokenDao, attrMap, keyMap);

    }


}