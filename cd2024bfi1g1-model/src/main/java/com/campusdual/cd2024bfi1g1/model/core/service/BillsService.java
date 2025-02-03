package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IBillsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.BillsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.MeasurementsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public EntityResult billsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {

        return this.daoHelper.query(this.billsDao, keyMap, attrList,"bill");
    }
    @Override
    public AdvancedEntityResult billsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.billsDao, keysValues, attributes, pagesize, offset, orderBy);
    }
    @Override
    public EntityResult billsInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        float precio_fijo = 3.4f;
        float precio_dispositivo = 1.5f;
        float precio_cada_mil_peticiones = 10.2f;
        int numero_disp_activos;
        int numero_peticiones;
        int CMP_ID = 1;
        String startDate = "2025-01-01";
        String endDate = "2025-02-01";

        Map<String, Object> filter = Map.of(DevicesDao.CMP_ID, CMP_ID,
                MeasurementsDao.ME_DATE, startDate,
                MeasurementsDao.ME_DATE, endDate);

        List<String> columns = List.of(
                "device_count",
                "measurement_count"
        );

        // Consultar la base de datos para obtener el ID del dispositivo y dev_persistence
        EntityResult query = billsQuery(filter, columns);
        Object resultDispositivos = query.get("device_count");
        Object resultPeticiones = query.get("measurement_count");


        int peticionesRedondeadas = (int) Math.ceil((double) (int) resultPeticiones / 1000);
        float gasto_total = precio_fijo + (precio_dispositivo * (int) resultDispositivos) + (peticionesRedondeadas * precio_cada_mil_peticiones);

        //if(LocalDateTime.now().getDayOfMonth() < 2) {}

        return this.daoHelper.insert(this.billsDao, attrMap);
    }

    @Override
    public EntityResult billsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.billsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult billsDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.billsDao, keyMap);
    }
}