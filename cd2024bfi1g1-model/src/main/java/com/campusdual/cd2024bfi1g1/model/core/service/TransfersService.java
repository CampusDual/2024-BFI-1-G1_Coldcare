package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransfersService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersLotsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.TransfersDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("TransfersService")
@Lazy
public class TransfersService implements ITransfersService {
    @Autowired
    private TransfersDao transfersDao;
    @Autowired
    private ContainersLotsDao clDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;

    @Override
    public EntityResult transfersQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.transfersDao, keyMap, attrList);
    }

    @Override
    public EntityResult transfersOriginQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Map<String, Object> filterByDestiny = new HashMap<>();
        filterByDestiny.put(transfersDao.TRA_DESTINY_CL, keyMap.get(clDao.CL_ID));

        return this.daoHelper.query(this.transfersDao, filterByDestiny, attrList, "origin");
    }

    @Override
    public EntityResult transfersDestinyQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        Map<String, Object> filterByOrigin = new HashMap<>();
        filterByOrigin.put(transfersDao.TRA_ORIGIN_CL, keyMap.get(clDao.CL_ID));

        return this.daoHelper.query(this.transfersDao, filterByOrigin, attrList, "destiny");
    }

    public EntityResult transfersOriginInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {

        try {
            if (!Objects.equals(attrMap.get(transfersDao.TRA_ORIGIN_CL), attrMap.get(clDao.CL_ID))) {
                Map<String, Object> valuesOriginInsert = new HashMap<>();
                valuesOriginInsert.put(transfersDao.TRA_ORIGIN_CL, attrMap.get(transfersDao.TRA_ORIGIN_CL));
                valuesOriginInsert.put(transfersDao.TRA_DESTINY_CL, attrMap.get(clDao.CL_ID));

                return this.daoHelper.insert(this.transfersDao, valuesOriginInsert);
            }
            else {
                return Util.controlErrors("ERROR_SAME_CONTAINER");
            }
        } catch (OntimizeJEERuntimeException ex) {
            throw new OntimizeJEERuntimeException("Algo salio mal al insertar datos");
        }

    }

    public EntityResult transfersDestinyInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {

        try {
            if (!Objects.equals(attrMap.get(transfersDao.TRA_DESTINY_CL), attrMap.get(clDao.CL_ID))) {
                Map<String, Object> valuesDestinyInsert = new HashMap<>();
                valuesDestinyInsert.put(transfersDao.TRA_DESTINY_CL, attrMap.get(transfersDao.TRA_DESTINY_CL));
                valuesDestinyInsert.put(transfersDao.TRA_ORIGIN_CL, attrMap.get(clDao.CL_ID));

                return this.daoHelper.insert(this.transfersDao, valuesDestinyInsert);
            }
            else {
                return Util.controlErrors("ERROR_SAME_CONTAINER");
            }
        } catch (OntimizeJEERuntimeException ex) {
            throw new OntimizeJEERuntimeException("Algo salio mal al insertar datos");
        }
    }

    @Override
    public EntityResult transfersUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.transfersDao, attrMap, keyMap);
    }

    @Override
    public EntityResult transfersDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
            return this.daoHelper.delete(this.transfersDao, keyMap);
    }
}

