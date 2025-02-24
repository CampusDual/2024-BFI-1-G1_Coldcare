package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransportsService;
import com.campusdual.cd2024bfi1g1.model.core.util.Util;
import com.ontimize.jee.common.db.SQLStatementBuilder;
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

import java.util.*;

import static com.campusdual.cd2024bfi1g1.model.core.util.Util.getUserId;

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

        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(TransportsDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.transportsDao, keyMap, attrList);
    }

    @Override
    public EntityResult transportsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(DevicesDao.CMP_ID, cmpId);

        return this.daoHelper.insert(this.transportsDao, attrMap);
    }

    @Override
    public EntityResult transportsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {

        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        attrMap.put(DevicesDao.CMP_ID, cmpId);
        
        if (attrMap.containsKey(TransportsDao.TST_ID)) {
            Object tstIdValue = attrMap.get(TransportsDao.TST_ID);

            if (tstIdValue instanceof Number && ((Number) tstIdValue).intValue() == 2) {
                Integer userId = Util.getUserId();
                attrMap.put(TransportsDao.USR_ID, userId);

            }
        }

        return this.daoHelper.update(this.transportsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult transportsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.transportsDao, keyMap);
    }
    @Override
    public EntityResult transportsMovilityQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(TransportsDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "movility");
    }
    @Override
    public EntityResult transportsLocationQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(TransportsDao.CMP_ID, cmpId);
        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "locations");
    }
    @Override
    public EntityResult transportsPlateQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = Util.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(VehiclesDao.CMP_ID, cmpId);

        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "plates");
    }
    @Override
    public EntityResult transportsPerUserQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        Integer cmpId = UserAndRoleService.getUserCompanyId(this.daoHelper, this.userDao);
        keyMap.put(VehiclesDao.CMP_ID, cmpId);

        Integer userId = getUserId();

        if (userId != null) {

            SQLStatementBuilder.BasicField transportUserField = new SQLStatementBuilder.BasicField(TransportsDao.USR_TRP_ID);
            SQLStatementBuilder.BasicField vehicleUserField = new SQLStatementBuilder.BasicField(VehiclesDao.USR_ID);

            // Expresión: transportUserField = userId
            SQLStatementBuilder.BasicExpression userIsTransporter = new SQLStatementBuilder.BasicExpression(transportUserField, SQLStatementBuilder.BasicOperator.EQUAL_OP, userId);

            // Expresión: vehicleUserField = userId
            SQLStatementBuilder.BasicExpression userWasAssignedToVehicle = new SQLStatementBuilder.BasicExpression(vehicleUserField, SQLStatementBuilder.BasicOperator.EQUAL_OP, userId);

            SQLStatementBuilder.BasicExpression transportUserNull = new SQLStatementBuilder.BasicExpression(transportUserField, SQLStatementBuilder.BasicOperator.NULL_OP, null);

            // Expresión final: (transportUserField = userId) OR (vehicleUserField = userId)
            SQLStatementBuilder.BasicExpression userWasAsgnToVhcAndTrUsrNull = new SQLStatementBuilder.BasicExpression(userWasAssignedToVehicle, SQLStatementBuilder.BasicOperator.AND_OP, transportUserNull);

            SQLStatementBuilder.BasicExpression userHasAccess = new SQLStatementBuilder.BasicExpression(userIsTransporter, SQLStatementBuilder.BasicOperator.OR_OP, userWasAsgnToVhcAndTrUsrNull);



            keyMap.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, userHasAccess);
        }

        return this.daoHelper.query(this.transportsDao, keyMap, attrList, "defaultPerState");
    }

}
