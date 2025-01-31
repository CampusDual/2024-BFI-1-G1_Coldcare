package com.campusdual.cd2024bfi1g1.model.core.service;


import com.campusdual.cd2024bfi1g1.api.core.service.IVehiclesService;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.VehiclesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    @Override
    public EntityResult vehiclesInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.vehiclesDao, attrMap);
    }

    @Override
    public EntityResult vehiclesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.vehiclesDao, attrMap, keyMap);
    }

    @Override
    public EntityResult vehiclesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.vehiclesDao, keyMap);
    }
}
