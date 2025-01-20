package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IContainersService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ContainersService")
@Lazy
public class ContainersService implements IContainersService {

    @Autowired
    private ContainersDao containersDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    private boolean changeContainerName(Integer userId, Map<String, Object> attrMap) {
        // Obtener la lista de containers del usuario
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(ContainersDao.USR_ID, userId);
        List<String> attrList = new ArrayList<>();
        attrList.add(ContainersDao.CNT_NAME);
        EntityResult existingContainers = this.containersQuery(keyMap, attrList);

        // Verificar si el nombre del nuevo container ya existe
        String newContainerName = (String) attrMap.get(ContainersDao.CNT_NAME);

        Integer size = newContainerName.length();
        Integer sizeTrim = newContainerName.trim().length();
        String trimmedContainerName = newContainerName.trim();

        if(trimmedContainerName.equals("")) {
            trimmedContainerName=null;
        }

            List<String> existingContainerNames = new ArrayList<>();
            for (int i = 0; i < existingContainers.calculateRecordNumber(); i++) {
                existingContainerNames.add((String) existingContainers.getRecordValues(i).get(ContainersDao.CNT_NAME));
            }
            for (Object containerName : existingContainerNames) {
                if (trimmedContainerName.equals(containerName)) {
                    return true;
                }
            }

        return false;
    }

    @Override
    public EntityResult containersQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Integer userId = Util.getId("usr_id");
        keyMap.put(ContainersDao.USR_ID, userId);

        return this.daoHelper.query(this.containersDao, keyMap, attrList);
    }

    @Override
    public EntityResult containersInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

        Integer userId = Util.getId("usr_id");
        attrMap.put(ContainersDao.USR_ID, userId);

        if(changeContainerName(userId,attrMap)){
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                    "Ya existe un contenedor con ese nombre");
        }

        return this.daoHelper.insert(this.containersDao, attrMap);
    }

    @Override
    public EntityResult containersUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {

        Integer userId = Util.getId("usr_id");
        attrMap.put(ContainersDao.USR_ID, userId);

        if(changeContainerName(userId,attrMap)){
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                    "Ya existe un contenedor con ese nombre");
        }

        return this.daoHelper.update(this.containersDao, attrMap, keyMap);
    }

    @Override
    public EntityResult containersDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.containersDao, keyMap);
    }

}
