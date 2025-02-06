package com.campusdual.cd2024bfi1g1.model.core.service;


import com.campusdual.cd2024bfi1g1.api.core.service.ILotsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.LotsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.ontimize.jee.common.db.NullValue;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
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

        EntityResult res = validarCamposTemp(attrMap);
        if (res != null) return res;

        return this.daoHelper.insert(this.lotsDao, attrMap);
    }

    @Override
    public EntityResult lotsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        Object lotId = keyMap.get("LOT_ID");

        if (!attrMap.containsKey("MIN_TEMP") && !attrMap.containsKey("MAX_TEMP")) {
            return this.daoHelper.update(this.lotsDao, attrMap, keyMap);
        }

        if ( attrMap.containsKey("MIN_TEMP") && attrMap.get("MIN_TEMP") instanceof NullValue && attrMap.containsKey("MAX_TEMP") && attrMap.get("MAX_TEMP") instanceof NullValue) {
            throw new OntimizeJEERuntimeException("Debes proporcionar al menos un valor para 'min_temp' o 'max_temp'.");
        }

        if (attrMap.containsKey("MIN_TEMP") && attrMap.get("MIN_TEMP") instanceof NullValue) {

            double maxTemp = getMaxTempForLotId(lotId);
            if (Double.isNaN(maxTemp)) {
                if (!attrMap.containsKey("MAX_TEMP")) {
                    throw new OntimizeJEERuntimeException("No pueden ser ambos nulos");
                }
            }

            return this.daoHelper.update(this.lotsDao, attrMap, keyMap);
        }

        if (attrMap.containsKey("MAX_TEMP") && attrMap.get("MAX_TEMP") instanceof NullValue) {

            double minTemp = getMinTempForLotId(lotId);
            if (Double.isNaN(minTemp)) {
                if (!attrMap.containsKey("MIN_TEMP")) {
                    throw new OntimizeJEERuntimeException("No pueden ser ambos nulos");
                }
            }
            return this.daoHelper.update(this.lotsDao, attrMap, keyMap);
        }

        if (attrMap.containsKey("MIN_TEMP") && !attrMap.containsKey("MAX_TEMP")) {
            Object minTemp = attrMap.get("MIN_TEMP");
            if (minTemp != null) {
                double maxTemp = getMaxTempForLotId(lotId);
                if (!Double.isNaN(maxTemp)) {
                    attrMap.put("MAX_TEMP", maxTemp);
                }
            }
        } else if (attrMap.containsKey("MAX_TEMP") && !attrMap.containsKey("MIN_TEMP")) {
            Object maxTemp = attrMap.get("MAX_TEMP");
            if (maxTemp != null) {
                double minTemp = getMinTempForLotId(lotId);
                if (!Double.isNaN(minTemp)) {
                    attrMap.put("MIN_TEMP", minTemp);
                }
            }
        }
        EntityResult res = validarCamposTemp(attrMap);
        if (res != null) return res;

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

    private EntityResult validarCamposTemp(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Object minTemp = attrMap.get("MIN_TEMP");
        Object maxTemp = attrMap.get("MAX_TEMP");

        if (minTemp == null && maxTemp == null) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("MEASUREMENTS_INSERT_ERROR");
            return res;
        }

        if (minTemp != null && maxTemp != null) {
            try {
                Float minTempValue = Float.parseFloat(minTemp.toString());
                Float maxTempValue = Float.parseFloat(maxTemp.toString());

                if (minTempValue >= maxTempValue) {
                    EntityResult res = new EntityResultMapImpl();
                    res.setCode(EntityResult.OPERATION_WRONG);
                    res.setMessage("MEASUREMENTS_INVALID_ERROR");
                    return res;
                }
            } catch (NumberFormatException e) {
                EntityResult res = new EntityResultMapImpl();
                res.setCode(EntityResult.OPERATION_WRONG);
                res.setMessage("MEASUREMENTS_DATA_ERROR");
                return res;
            }
        }
        return null;
    }

    public double getMaxTempForLotId(Object lotId) {
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("LOT_ID", lotId);

        List<String> attrList = new ArrayList<>();
        attrList.add("MAX_TEMP");

        EntityResult result = this.daoHelper.query(this.lotsDao, keyMap, attrList, "get_max_temp_lot_Id");

        if (result != null && result.calculateRecordNumber() > 0) {
            Object maxTempValue = result.get("MAX_TEMP");

            if (maxTempValue instanceof List) {
                List<?> tempList = (List<?>) maxTempValue;
                if (!tempList.isEmpty()) {
                    Object firstValue = tempList.get(0);
                    if (firstValue instanceof Number) {
                        return ((Number) firstValue).doubleValue();
                    }
                }
            }
        }

        return Double.NaN;
    }


    public double getMinTempForLotId(Object lotId) {
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("LOT_ID", lotId);

        List<String> attrList = new ArrayList<>();
        attrList.add("MIN_TEMP");

        EntityResult result = this.daoHelper.query(this.lotsDao, keyMap, attrList, "get_min_temp_lot_Id");

        if (result != null && result.calculateRecordNumber() > 0) {
            Object minTempValue = result.get("MIN_TEMP");

            if (minTempValue instanceof List) {
                List<?> tempList = (List<?>) minTempValue;
                if (!tempList.isEmpty()) {
                    Object firstValue = tempList.get(0);
                    if (firstValue instanceof Number) {
                        return ((Number) firstValue).doubleValue();
                    }
                }
            }
        }

        return Double.NaN;
    }




}
