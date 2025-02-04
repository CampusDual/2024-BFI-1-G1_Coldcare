package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IBillsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.BillsDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BillsService")
@Lazy

public class BillsService implements IBillsService {
    @Autowired
    private BillsDao billsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult billsQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.billsDao, keyMap, attrList);
    }

    @Override
    public EntityResult billsDataQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.billsDao, keyMap, attrList, "monthlyData");
    }

    @Override
    public AdvancedEntityResult billsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.billsDao, keysValues, attributes, pagesize, offset, orderBy);
    }

    @Override
    public EntityResult billsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.billsDao, attrMap);
    }

    @Override
    public EntityResult billsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.billsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult billsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.billsDao, keyMap);
    }

    public void createBills() {

        int year = LocalDate.now().minusMonths(1).getYear();
        int month = LocalDate.now().minusMonths(1).getMonthValue();
        Map<String, Object> keyMapData = new HashMap<>();
        keyMapData.put("year", year);
        keyMapData.put("month", month);
        EntityResult resultCMP = billsDataQuery(keyMapData, List.of("cmp_id"));
        for (int i = 0; i < resultCMP.calculateRecordNumber(); i++) {
            if (resultCMP.getRecordValues(i).get("cmp_id") != null) {
                keyMapData.put("cmp_id", resultCMP.getRecordValues(i).get("cmp_id"));
                EntityResult resultData = billsDataQuery(keyMapData, List.of("device_count", "measurement_count"));

                int nActiveDevices = 0;
                int nPetitions = 0;
                float price = 3.4f;
                float devicePrice = 1.5f;
                float price1000 = 10.2f;

                if (resultData != null && resultData.calculateRecordNumber() > 0) {
                    nActiveDevices = ((Number) resultData.getRecordValues(0).get("device_count")).intValue();
                    nPetitions = ((Number) resultData.getRecordValues(0).get("measurement_count")).intValue();
                }

                float totalExpense = price + (devicePrice * nActiveDevices) + (float) Math.ceil(nPetitions / 1000.0) * price1000;

                Map<String, Object> billData = new HashMap<>();
                billData.put(BillsDao.CMP_ID, resultCMP.getRecordValues(i).get("cmp_id"));
                billData.put(BillsDao.BIL_DATE, LocalDate.of(year, month, 1));
                billData.put(BillsDao.BIL_DEVICES, nActiveDevices);
                billData.put(BillsDao.BIL_MEASUREMENTS, nPetitions);
                billData.put(BillsDao.BIL_EXPENSE, totalExpense);

                this.billsInsert(billData);
            } else {
                return;
            }

        }
    }
}
