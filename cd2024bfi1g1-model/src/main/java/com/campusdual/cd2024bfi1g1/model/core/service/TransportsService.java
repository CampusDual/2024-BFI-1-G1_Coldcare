package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransportsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.TransportsDao;
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

@Service("TransportsService")
@Lazy
public class TransportsService implements ITransportsService {

    @Autowired
    private TransportsDao transportsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;


    @Override
    public EntityResult transportsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(TransportsDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.transportsDao, keyMap, attrList);
    }

    @Override
    public EntityResult transportsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(DevicesDao.CMP_ID, cmpId);

        return this.daoHelper.insert(this.transportsDao, attrMap);
    }

    @Override
    public EntityResult transportsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {

        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(DevicesDao.CMP_ID, cmpId);

        return this.daoHelper.update(this.transportsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult transportsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.transportsDao, keyMap);
    }
    @Override
    public EntityResult transportsMovilityQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(TransportsDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "movility");
    }
    @Override
    public EntityResult transportsLocationQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(TransportsDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "locations");
    }
    @Override
    public EntityResult transportsPlateQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(VehiclesDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "plates");
    }

}
