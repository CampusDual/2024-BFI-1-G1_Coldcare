package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IMeasurementsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.service.DevicesService;
import com.campusdual.cd2024bfi1g1.model.core.dao.MeasurementsDao;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.security.access.annotation.Secured;
import com.ontimize.jee.common.security.PermissionsProviderSecured;

@Service("MeasurementsService")
@Lazy
public class MeasurementsService implements IMeasurementsService {

    @Autowired
    private MeasurementsDao measurementsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private DevicesService devicesService;

    @Override
    public EntityResult measurementsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.measurementsDao, keyMap, attrList);
    }

    @Override
    public AdvancedEntityResult measurementsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.measurementsDao, keysValues, attributes, pagesize, offset, orderBy);
    }

    @Override
    public EntityResult measurementsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(DevicesDao.DEV_MAC, (String) attrMap.get(DevicesDao.DEV_MAC));

        // Obtener los atributos necesarios para la consulta
        List<String> columns = new ArrayList<>();
        columns.add(DevicesDao.DEV_ID);
        columns.add(DevicesDao.DEV_MAC);
        columns.add(DevicesDao.DEV_PERSISTENCE); // Obtener el campo dev_persistence

        // Consultar la base de datos para obtener el ID del dispositivo y dev_persistence
        EntityResult query = devicesService.devicesQuery(filter, columns);

        // Verificar si el dispositivo ya existe
        if (!query.isEmpty() && !query.isWrong()) {
            Map<String, Object> rowDevice = query.getRecordValues(0);
            attrMap.put(MeasurementsDao.DEV_ID, rowDevice.get(DevicesDao.DEV_ID));
            filter.clear();
            filter.put(DevicesDao.DEV_ID, rowDevice.get(DevicesDao.DEV_ID));
            //columns.add(MeasurementsDao.ME_DATE);
            EntityResult lastTimeResult = devicesService.lastTimeWithoutCMP(filter, columns);

            // Obtener el valor de dev_persistence (en minutos)
            Integer persistenceMinutes = (Integer) rowDevice.get(DevicesDao.DEV_PERSISTENCE);

            if (persistenceMinutes == null) {
                persistenceMinutes = 1;
            }

            if (!lastTimeResult.isEmpty() && !lastTimeResult.isWrong()) {
                // Obtener el valor de la última medición (me_date)
                Map<String, Object> rowLastTime = lastTimeResult.getRecordValues(0);
                Timestamp lastDateObj = (Timestamp) rowLastTime.get(MeasurementsDao.ME_DATE);

                    // Comprobar si han pasado más de los minutos configurados
                    long currentTimeMillis = System.currentTimeMillis();
                    long lastTimeMillis = lastDateObj.getTime(); // Obtenemos el tiempo en milisegundos de la última medición
                    long persistenceMillis = persistenceMinutes * 60000L; // Convertimos los minutos a milisegundos

                    if (currentTimeMillis - lastTimeMillis > persistenceMillis) {
                        return this.daoHelper.insert(this.measurementsDao, attrMap);
                    } else {
                        return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                                "Esperar más de " + persistenceMinutes + " minutos para insertar una nueva medición.");
                    }

            } else {
                return this.daoHelper.insert(this.measurementsDao, attrMap);
            }

        } else if (query.isEmpty()) {
            // Si no se encuentra el dispositivo, se inserta uno nuevo
            EntityResult mac_insert = devicesService.devicesInsert(filter);
            attrMap.put(MeasurementsDao.DEV_ID, mac_insert.get(DevicesDao.DEV_ID));
            return this.daoHelper.insert(this.measurementsDao, attrMap);
        } else {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "No se ha podido realizar la query");
        }
    }

    @Override
    public EntityResult measurementsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.measurementsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult measurementsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.measurementsDao, keyMap);
    }

}