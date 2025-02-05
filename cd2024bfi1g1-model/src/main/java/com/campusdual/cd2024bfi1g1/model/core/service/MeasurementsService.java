package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IMeasurementsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.AlertsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.MeasurementsDao;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.SQLStatementBuilder;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service("MeasurementsService")
@Lazy
public class MeasurementsService implements IMeasurementsService {

    @Autowired
    private MeasurementsDao measurementsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private DevicesService devicesService;
    @Autowired
    private LotsService lotsService;
    @Autowired
    private AlertsService alertsService;

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
        Map<String, Object> filter = Map.of(DevicesDao.DEV_MAC, (String) attrMap.get(DevicesDao.DEV_MAC));

        List<String> columns = List.of(
                DevicesDao.DEV_ID,
                DevicesDao.DEV_MAC,
                DevicesDao.DEV_PERSISTENCE,
                DevicesDao.DEV_STATE
        );

        // Consultar la base de datos para obtener el ID del dispositivo y dev_persistence
        EntityResult query = devicesService.devicesQuery(filter, columns);
        if(query.isEmpty()){
            EntityResult mac_insert = devicesService.devicesInsert(filter);
            attrMap.put(MeasurementsDao.DEV_ID, mac_insert.get(DevicesDao.DEV_ID));
            return this.daoHelper.insert(this.measurementsDao, attrMap);
        }
        if(query.isWrong()){
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "No se ha podido realizar la query");
        }
        // Verificar si el dispositivo ya existe
        Map<String, Object> rowDevice = query.getRecordValues(0);

        Boolean devState = (Boolean) rowDevice.get(DevicesDao.DEV_STATE);
        if (devState != null && !devState) {
            return new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.NODATA_RESULT);
        }
        Integer devId = (Integer) rowDevice.get(DevicesDao.DEV_ID);
        attrMap.put(MeasurementsDao.DEV_ID, devId);

        Map<String, Object> containerLotFilter = Map.of(DevicesDao.DEV_MAC, attrMap.get(DevicesDao.DEV_MAC));
        List<String> containerLotColumns = List.of("CNT_ID", "LOT_ID");

        EntityResult containerLotResult = this.daoHelper.query(this.measurementsDao, containerLotFilter, containerLotColumns, "container_lot");
        if (containerLotResult.isEmpty()) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "Error querying container_lot");
        }
        Map<String, Object> containerLotRow = containerLotResult.getRecordValues(0);
        Integer cntId = (Integer) containerLotRow.get("CNT_ID");
        Integer lotId = (Integer) containerLotRow.get("LOT_ID");

        attrMap.put(MeasurementsDao.CNT_ID, cntId);
        attrMap.put(MeasurementsDao.LOT_ID, lotId);

        Double temp = (Double) attrMap.get(MeasurementsDao.ME_TEMP);
        Integer meId = (Integer) attrMap.get(MeasurementsDao.ME_ID);
        Integer altId = this.computeMeasurementAlert(temp, devId, lotId, cntId, meId);

        EntityResult lastTimeResult = devicesService.lastTimeWithoutCMP(
                Map.of(DevicesDao.DEV_ID, rowDevice.get(DevicesDao.DEV_ID)),
                columns
        );

        // Obtener el valor de dev_persistence (en minutos)
        Integer persistenceMinutes = (Integer) rowDevice.get(DevicesDao.DEV_PERSISTENCE);

        EntityResult entityResult;

        if (!lastTimeResult.isEmpty() && !lastTimeResult.isWrong()) {
            // Obtener el valor de la última medición (me_date)
            Map<String, Object> rowLastTime = lastTimeResult.getRecordValues(0);
            Timestamp lastDateObj = (Timestamp) rowLastTime.get(MeasurementsDao.ME_DATE);

                // Comprobar si han pasado más de los minutos configurados
                long currentTimeMillis = System.currentTimeMillis();
                long lastTimeMillis = lastDateObj.getTime(); // Obtenemos el tiempo en milisegundos de la última medición
                long persistenceMillis = persistenceMinutes * 60000L; // Convertimos los minutos a milisegundos

                if (currentTimeMillis - lastTimeMillis > persistenceMillis) {
                    entityResult = this.daoHelper.insert(this.measurementsDao, attrMap);
                } else {
                    return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                            "Esperar más de " + persistenceMinutes + " minutos para insertar una nueva medición.");
                    }

        } else {
            entityResult = this.daoHelper.insert(this.measurementsDao, attrMap);
        }

        if (altId == null) {
            return entityResult;
        }

        Integer lastMeasurementId = (Integer) entityResult.get(MeasurementsDao.ME_ID);

        Map<String, Object> valuesToUpdate = Map.of(
                MeasurementsDao.ALT_ID, altId
        );
        Map<String, Object> filterToUpdate = Map.of(MeasurementsDao.ME_ID, lastMeasurementId);
        this.measurementsUpdate(valuesToUpdate, filterToUpdate);
        return entityResult;
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

    @Override
    public EntityResult ContainerLotQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.measurementsDao, keyMap, attrList, "container_lot");
    }

    private Integer computeMeasurementAlert(Double currentTemp, Integer devId, Integer lotId, Integer cntId, Integer meId) {
        Double lastTemp = getLastRecordedTemperature(devId);
        if (lastTemp == null) {
            return null;
        }

        Double minTemp = lotsService.getMinTempForLotId(lotId);
        Double maxTemp = lotsService.getMaxTempForLotId(lotId);

        boolean isError = (currentTemp > maxTemp || currentTemp < minTemp);
        boolean wasError = (lastTemp > maxTemp || lastTemp < minTemp);

        if (isError && !wasError) {
            createAlert(minTemp, maxTemp, cntId, lotId);
        } else if (!isError && wasError) {
            closeLastAlert(cntId, lotId);
        }

        return isError ? getLastAlertId(cntId, lotId) : null;
    }

    private Double getLastRecordedTemperature(Integer devId) {
        Map<String, Object> filter = Map.of(DevicesDao.DEV_ID, devId);
        List<String> columns = List.of(MeasurementsDao.ME_TEMP);
        List<SQLStatementBuilder.SQLOrder> orderBy = List.of(
                new SQLStatementBuilder.SQLOrder(MeasurementsDao.ME_DATE, false)
        );

        EntityResult eR = measurementsPaginationQuery(filter, columns, 1, 0, orderBy);
        if (eR.isEmpty() || eR.isWrong()) {
            return null;
        }

        return (Double) eR.getRecordValues(0).get(MeasurementsDao.ME_TEMP);
    }

    private void createAlert(Double minTemp, Double maxTemp, Integer cntId, Integer lotId) {
        Map<String, Object> valuesToInsert = Map.of(
                AlertsDao.ALT_MIN_TEMP, minTemp,
                AlertsDao.ALT_MAX_TEMP, maxTemp,
                AlertsDao.CNT_ID, cntId,
                AlertsDao.LOT_ID, lotId
        );
        alertsService.alertsInsert(valuesToInsert);
    }

    private void closeLastAlert(Integer cntId, Integer lotId) {
        Integer lastAlertId = getLastAlertId(cntId, lotId);
        if (lastAlertId == null) {
            return;
        }

        Map<String, Object> valuesToUpdate = Map.of(AlertsDao.ALT_DATE_END, LocalDateTime.now());
        Map<String, Object> filterToUpdate = Map.of(AlertsDao.ALT_ID, lastAlertId);
        alertsService.alertsUpdate(valuesToUpdate, filterToUpdate);
    }

    private Integer getLastAlertId(Integer cntId, Integer lotId) {
        Map<String, Object> filter = Map.of(
                AlertsDao.CNT_ID, cntId,
                AlertsDao.LOT_ID, lotId
        );
        List<String> columns = List.of(AlertsDao.ALT_ID);
        List<SQLStatementBuilder.SQLOrder> orderBy = List.of(
                new SQLStatementBuilder.SQLOrder(AlertsDao.ALT_DATE_INIT, false)
        );

        EntityResult eR = alertsService.alertsPaginationQuery(filter, columns, 1, 0, orderBy);
        if (eR.isEmpty() || eR.isWrong()) {
            return null;
        }

        return (Integer) eR.getRecordValues(0).get(AlertsDao.ALT_ID);
    }
}