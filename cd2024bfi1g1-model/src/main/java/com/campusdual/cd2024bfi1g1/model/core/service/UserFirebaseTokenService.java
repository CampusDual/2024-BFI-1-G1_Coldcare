package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IUserFirebaseTokenService;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserFirebaseTokenDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        attrMap.put(UserFirebaseTokenDao.USR_ID, userId);

        return this.daoHelper.insert(this.userFirebaseTokenDao, attrMap);
    }

    @Override
    public EntityResult getAllTokens(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.userFirebaseTokenDao, keyMap, attrList, "all_tokens");
    }

}