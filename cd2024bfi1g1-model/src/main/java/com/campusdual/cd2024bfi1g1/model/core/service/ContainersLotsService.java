package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IContainersLotsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersLotsDao;
import com.ontimize.jee.common.db.SQLStatementBuilder;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ontimize.jee.common.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicField;

import java.util.*;

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

        try {
            if (filterStartAndEndDates(attrMap) && validateStartAndEndDates(attrMap))
                return this.daoHelper.insert(this.containersLotsDao, attrMap);
            else {
                return controlErrors();
            }
        } catch (OntimizeJEERuntimeException ex) {
            throw new OntimizeJEERuntimeException("Algo salio mal al insertar datos");
        }

    }

    @Override
    public EntityResult containersLotsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {

        try {
            Integer clId = (Integer) keyMap.get(ContainersLotsDao.CL_ID);
            Map<String, Object> valData = getContainerLotData(clId);

            if (filterStartAndEndDates(valData) && validateStartAndEndDates(valData)) {
                return this.daoHelper.update(this.containersLotsDao, attrMap, keyMap);
            } else {
                return controlErrors();
            }
        } catch (OntimizeJEERuntimeException ex) {
            throw new OntimizeJEERuntimeException("Algo salio mal al actualizar los datos");
        }
    }

    @Override
    public EntityResult containersLotsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        try {
            return this.daoHelper.delete(this.containersLotsDao, keyMap);
        } catch (DataIntegrityViolationException e) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("ERROR_CL_DELETE");
            return res;
        }
    }

    private boolean filterStartAndEndDates(Map<String, Object> attrMap) {

        //Creamos un nuevo mapa con las condiciones
        Map<String, Object> conditions = new HashMap<>(attrMap);
        conditions.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, searchBetweenWithDates(attrMap));
        conditions.remove(ContainersLotsDao.CL_START_DATE);
        conditions.remove(ContainersLotsDao.CL_END_DATE);
        conditions.remove(ContainersLotsDao.LOT_ID);

        List<String> queryColumns = Arrays.asList(ContainersLotsDao.CL_START_DATE, ContainersLotsDao.CL_END_DATE);

        //El CL_ID se necesita para hacer la Basic expresion pero no para la query (Se uas para el update)
        conditions.remove(ContainersLotsDao.CL_ID);

        EntityResult result = this.daoHelper.query(this.containersLotsDao, conditions, queryColumns, "get_dates");

        return result.isEmpty();
    }

    private boolean validateStartAndEndDates(Map<String, Object> data) {

        Date startDate = (Date) data.get(ContainersLotsDao.CL_START_DATE);
        Date endDate = (Date) data.get(ContainersLotsDao.CL_END_DATE);

        return startDate == null || endDate == null || startDate.equals(endDate) || endDate.after(startDate);
    }

    private Map<String, Object> getContainerLotData(Integer clId) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put(ContainersLotsDao.CL_ID, clId);

        List<String> queryColumns = Arrays.asList(
                ContainersLotsDao.CL_ID,
                ContainersLotsDao.CNT_ID,
                ContainersLotsDao.LOT_ID,
                ContainersLotsDao.CL_START_DATE,
                ContainersLotsDao.CL_END_DATE);

        EntityResult result = this.daoHelper.query(this.containersLotsDao, conditions, queryColumns);
        Map<String, Object> data = new HashMap<>(result.getRecordValues(0));

        return data;
    }

    private BasicExpression searchBetweenWithDates(Map<String, Object> dataToFilter) {

        if (!dataToFilter.containsKey(ContainersLotsDao.CL_START_DATE) && !dataToFilter.containsKey(ContainersLotsDao.CL_END_DATE)) {
            return null;
        }

        BasicField fieldStart = new BasicField(ContainersLotsDao.CL_START_DATE);
        BasicField fieldEnd = new BasicField(ContainersLotsDao.CL_END_DATE);

        Date startDate = (Date) dataToFilter.get(ContainersLotsDao.CL_START_DATE);

        BasicExpression startDateBetween = new BasicExpression(
                new BasicExpression(fieldStart, BasicOperator.LESS_EQUAL_OP, startDate),
                BasicOperator.AND_OP,
                new BasicExpression(fieldEnd, BasicOperator.MORE_OP, startDate)
        );

        BasicExpression endDateBetween, betweenStartAndEndDate, betweenStartWithoutEnd, bexComplete;
        if (dataToFilter.containsKey(ContainersLotsDao.CL_END_DATE)) {
            Date endDate = (Date) dataToFilter.get(ContainersLotsDao.CL_END_DATE);

            endDateBetween = new BasicExpression(
                    new BasicExpression(fieldStart, BasicOperator.LESS_OP, endDate),
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldEnd, BasicOperator.MORE_EQUAL_OP, endDate)
            );

            betweenStartAndEndDate = new BasicExpression(
                    new BasicExpression(fieldStart, BasicOperator.MORE_EQUAL_OP, startDate),
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldEnd, BasicOperator.LESS_EQUAL_OP, endDate)

            );

            betweenStartWithoutEnd = new BasicExpression(
                    new BasicExpression(fieldStart, BasicOperator.MORE_EQUAL_OP, startDate),
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldStart, BasicOperator.LESS_OP, endDate)
            );

            bexComplete = new BasicExpression(startDateBetween, BasicOperator.OR_OP, endDateBetween);
            bexComplete = new BasicExpression(bexComplete, BasicOperator.OR_OP, betweenStartAndEndDate);
            bexComplete = new BasicExpression(bexComplete, BasicOperator.OR_OP, betweenStartWithoutEnd);

        } else {
            BasicExpression endDateIsNull = new BasicExpression(fieldEnd, BasicOperator.NULL_OP, null);
            bexComplete = new BasicExpression(endDateIsNull, BasicOperator.OR_OP, startDateBetween);
        }


        if (dataToFilter.containsKey(ContainersLotsDao.CL_ID)) {
            Integer clId = (Integer) dataToFilter.get(ContainersLotsDao.CL_ID);
            BasicField fieldClId = new BasicField(ContainersLotsDao.CL_ID);
            BasicExpression excludeSameId = new BasicExpression(fieldClId, BasicOperator.NOT_EQUAL_OP, clId);
            bexComplete = new BasicExpression(excludeSameId, BasicOperator.AND_OP, bexComplete);
        }

        return bexComplete;
    }

    private EntityResult controlErrors() {
        EntityResult res = new EntityResultMapImpl();
        res.setCode(EntityResult.OPERATION_WRONG);
        res.setMessage("ERROR_DATE_ALREADY_EXIST");
        return res;
    }
}
