package com.campusdual.cd2024bfi1g1.model.core.service;


import com.campusdual.cd2024bfi1g1.api.core.service.IVehiclesService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.VehiclesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service ("VehiclesService")
@Lazy
public class VehiclesService implements IVehiclesService {

    @Autowired
    private VehiclesDao vehiclesDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;

    @Override
    public EntityResult vehiclesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(VehiclesDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.vehiclesDao, keyMap, attrList);
    }

    private boolean changeVehiclePlate(Integer cmpId, Map<String, Object> attrMap) {
        // Obtener la lista de vehiculos del usuario
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(VehiclesDao.VHC_ID, cmpId);
        List<String> attrList = new ArrayList<>();
        attrList.add(VehiclesDao.VHC_PLATE);
        EntityResult existingVehicles = this.vehiclesQuery(keyMap, attrList);

        // Verificar si el nombre del nuevo vehiculo ya existe
        String newVehiclePlate = (String) attrMap.get(VehiclesDao.VHC_PLATE);

        if (newVehiclePlate == null) {
            return false;
        }
        String trimmedContainerPlate = newVehiclePlate.trim();

        List<String> existingVehiclePlates = new ArrayList<>();
        for (int i = 0; i < existingVehicles.calculateRecordNumber(); i++) {
            existingVehiclePlates.add((String) existingVehicles.getRecordValues(i).get(VehiclesDao.VHC_PLATE));
        }
        for (Object vehiclePlate : existingVehiclePlates) {
            if (trimmedContainerPlate.equals(vehiclePlate)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public EntityResult vehiclesInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(VehiclesDao.CMP_ID, cmpId);
        if (changeVehiclePlate(cmpId, attrMap)) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                    "Ya existe un vehiculo con ese nombre");
        }
        return this.daoHelper.insert(this.vehiclesDao, attrMap);
    }

    @Override
    public EntityResult vehiclesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(VehiclesDao.CMP_ID, cmpId);
        if (changeVehiclePlate(cmpId, attrMap)) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                    "Ya existe un vehiculo con ese nombre");
        }
        return this.daoHelper.update(this.vehiclesDao, attrMap, keyMap);
    }

    @Override
    public EntityResult vehiclesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        try {
            return this.daoHelper.delete(this.vehiclesDao, keyMap);
        } catch (DataIntegrityViolationException e) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("VEHICLE_DELETE_ERROR");
            return res;
        }
    }

    @Override
    public EntityResult userTransporterQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(VehiclesDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.vehiclesDao, keyMap, attrList, "userTransporter");
    }

}
