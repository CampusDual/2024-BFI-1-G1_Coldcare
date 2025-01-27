package com.campusdual.cd2024bfi1g1.model.core.service;


import com.campusdual.cd2024bfi1g1.api.core.service.ILotsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.LotsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
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

import java.util.*;

@Service("LotsService")
@Lazy
public class LotsService implements ILotsService {

    @Autowired
    private LotsDao lotsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;

    private Integer getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Integer userId = null;

        if (principal instanceof UserInformation) {
            UserInformation userInfo = (UserInformation) principal;

            // Extraer el mapa otherData
            Map<Object, Object> rawOtherData = userInfo.getOtherData();

            userId = (Integer) rawOtherData.get("usr_id");
        }

        return userId;
    }

    @Override
    public EntityResult lotsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(DevicesDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.lotsDao, keyMap, attrList);
    }

    @Override
    public EntityResult lotsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(DevicesDao.CMP_ID, cmpId);

        validarCamposTemp(attrMap);

        return this.daoHelper.insert(this.lotsDao, attrMap);
    }

    @Override
    public EntityResult lotsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        // Obtener el LOT_ID del keyMap
        Object lotId = keyMap.get("LOT_ID");

        if (lotId == null) {
            throw new OntimizeJEERuntimeException("El LOT_ID no está presente en keyMap");
        }

        // Validar campos de temperatura
        validarCamposTemp(attrMap);

        // Comprobamos si solo viene MIN_TEMP o MAX_TEMP en attrMap
        if (attrMap.containsKey("MIN_TEMP") && !attrMap.containsKey("MAX_TEMP")) {
            // Solo viene MIN_TEMP, entonces buscamos MAX_TEMP en la base de datos
            Object minTemp = attrMap.get("MIN_TEMP");
            if (minTemp != null) {
                // Usamos el daoHelper para obtener MAX_TEMP según el LOT_ID
                Map<String, Object> tempMap = this.lotsDao.getMaxTempForLotId(lotId);
                if (tempMap != null && tempMap.containsKey("MAX_TEMP")) {
                    attrMap.put("MAX_TEMP", tempMap.get("MAX_TEMP"));
                }
            }
        } else if (attrMap.containsKey("MAX_TEMP") && !attrMap.containsKey("MIN_TEMP")) {
            // Solo viene MAX_TEMP, entonces buscamos MIN_TEMP en la base de datos
            Object maxTemp = attrMap.get("MAX_TEMP");
            if (maxTemp != null) {
                // Usamos el daoHelper para obtener MIN_TEMP según el LOT_ID
                Map<String, Object> tempMap = this.lotsDao.getMinTempForLotId(lotId);
                if (tempMap != null && tempMap.containsKey("MIN_TEMP")) {
                    attrMap.put("MIN_TEMP", tempMap.get("MIN_TEMP"));
                }
            }
        }

        // Llamada al DAO para hacer la actualización
        return this.daoHelper.update(this.lotsDao, attrMap, keyMap);
    }



    @Override
    public EntityResult lotsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.lotsDao, keyMap);
    }

    @Override
    public EntityResult lotsContainerQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.lotsDao, keyMap, attrList, "lots_container");
    }

    @Override
    public EntityResult historicLotContainerQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.lotsDao, keyMap, attrList, "historic_lot_container");
    }

    @Override
    public EntityResult measurementLotContainerQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.lotsDao, keyMap, attrList, "historic_lot_measurements");
    }

    private void validarCamposTemp(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Object minTemp = attrMap.get("MIN_TEMP");
        Object maxTemp = attrMap.get("MAX_TEMP");

        if (minTemp == null && maxTemp == null) {
            throw new OntimizeJEERuntimeException("Debes proporcionar al menos un valor para 'min_temp' o 'max_temp'.");
        }

        if (minTemp != null && maxTemp != null) {
            try {
                Float minTempValue = Float.parseFloat(minTemp.toString());
                Float maxTempValue = Float.parseFloat(maxTemp.toString());

                if (minTempValue > maxTempValue) {
                    throw new OntimizeJEERuntimeException("'min_temp' no puede ser mayor que 'max_temp'.");
                }
            } catch (NumberFormatException e) {
                throw new OntimizeJEERuntimeException("Los campos 'min_temp' y 'max_temp' deben ser valores numéricos.");
            }
        }
    }

}
