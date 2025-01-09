package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IContainersService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.MeasurementsDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ContainersService")
@Lazy
public class ContainersService implements IContainersService {

    @Autowired
    private ContainersDao containersDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    private Integer getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        Integer user_id = null;
        if (principal instanceof UserInformation) {
            UserInformation userInfo = (UserInformation) principal;

            // Extraer el mapa otherData
            Map<Object, Object> rawOtherData = userInfo.getOtherData();

            user_id = (Integer) rawOtherData.get("usr_id");
        }
        return user_id;
    }


    @Override
    public EntityResult containersQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Integer user_id = this.getUserId();
        keyMap.put(ContainersDao.USR_ID, user_id);

        return this.daoHelper.query(this.containersDao, keyMap, attrList);
    }

    @Override
    public EntityResult containersInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

        Integer user_id = this.getUserId();

        attrMap.put("USR_ID", user_id);

        return this.daoHelper.insert(this.containersDao, attrMap);
    }

    @Override
    public EntityResult containersUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.containersDao, attrMap, keyMap);
    }

    @Override
    public EntityResult containersDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.containersDao, keyMap);
    }

}
