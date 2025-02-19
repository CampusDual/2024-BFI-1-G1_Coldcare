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

    @Override
    public EntityResult transfersOriginInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return transfersInsert(attrMap, transfersDao.TRA_ORIGIN_CL, transfersDao.TRA_DESTINY_CL);
    }

    @Override
    public EntityResult transfersDestinyInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return transfersInsert(attrMap, transfersDao.TRA_DESTINY_CL, transfersDao.TRA_ORIGIN_CL);
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

    private EntityResult transfersInsert(Map<String, Object> attrMap, String originField, String destinyField)
            throws OntimizeJEERuntimeException {

        Map<String, Object> transferData = new HashMap<>();
        transferData.put(originField, attrMap.get(originField));
        transferData.put(destinyField, attrMap.get(clDao.CL_ID));

        List<String> attrList = Collections.singletonList(transfersDao.TRA_ID);

        EntityResult existingTransfers = transfersQuery(transferData, attrList);
        if (!existingTransfers.isEmpty()) {
            return Util.controlErrors("ERROR_TRANSFER_ALREADY_EXISTS");
        }

        if (Objects.equals(attrMap.get(originField), attrMap.get(clDao.CL_ID))) {
            return Util.controlErrors("ERROR_SAME_CONTAINER");
        }

        return this.daoHelper.insert(this.transfersDao, transferData);
    }

}
