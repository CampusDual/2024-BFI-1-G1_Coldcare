package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IContainersLotsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersLotsDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ContainersLotsService")
@Lazy
public class ContainersLotsService implements IContainersLotsService {

    @Autowired
    private ContainersLotsDao containersLotsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;


    @Override
    public EntityResult containersLotsQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.containersLotsDao, keyMap, attrList);
    }

    @Override
    public EntityResult containersLotsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.containersLotsDao, attrMap);
    }

    @Override
    public EntityResult containersLotsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.containersLotsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult containersLotsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        try{
            return this.daoHelper.delete(this.containersLotsDao, keyMap);
        }
        catch(DataIntegrityViolationException e) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("ERROR_CL_DELETE");
            return res;
        }
    }
}
