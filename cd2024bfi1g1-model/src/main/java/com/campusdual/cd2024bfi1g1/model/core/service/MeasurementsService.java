package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IMeasurementsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.*;

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
        Integer devId = getDeviceId(attrMap);
        if (devId == null) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "No se ha podido realizar la query");
        }

        attrMap.put(MeasurementsDao.DEV_ID, devId);

        Map<String, Integer> containerLot = getContainerLot(attrMap);
        if (containerLot == null) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "Error querying container_lot");
        }

        Integer cntId = containerLot.get(ContainersDao.CNT_ID);
        Integer lotId = containerLot.get(MeasurementsDao.LOT_ID);

        attrMap.put(MeasurementsDao.CNT_ID, cntId);
        attrMap.put(MeasurementsDao.LOT_ID, lotId);

        Integer persistenceMinutes = getPersistenceMinutes(devId);
        if (persistenceMinutes == null) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "Error obteniendo persistencia del dispositivo");
        }

        if (!canInsertMeasurement(devId, persistenceMinutes)) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                    "Esperar más de " + persistenceMinutes + " minutos para insertar una nueva medición.");
        }

        Double currentTemp = (Double) attrMap.get(MeasurementsDao.ME_TEMP);
        Integer meId = (Integer) attrMap.get(MeasurementsDao.ME_ID);

        Integer altId = computeMeasurementAlert( currentTemp, devId, lotId, cntId, meId );

        EntityResult eR = daoHelper.insert(measurementsDao, attrMap);
        if (altId != null) {
            updateMeasurementAlert(eR, altId);
        }
        return eR;
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

    private Integer getDeviceId(Map<String, Object> attrMap) {
        Map<String, Object> filter = Map.of(DevicesDao.DEV_MAC, attrMap.get(DevicesDao.DEV_MAC));
        List<String> columns = List.of(
                DevicesDao.DEV_ID,
                DevicesDao.DEV_MAC,
                DevicesDao.DEV_PERSISTENCE,
                DevicesDao.DEV_STATE
        );

        EntityResult query = devicesService.devicesQuery(filter, columns);
        if (query.isEmpty()) {
            EntityResult eR = devicesService.devicesInsert(filter);
            return (Integer) eR.get(DevicesDao.DEV_ID);
        }
        if (query.isWrong()) {
            return null;
        }

        Map<String, Object> rowDevice = query.getRecordValues(0);
        Boolean enabled = (Boolean) rowDevice.get(DevicesDao.DEV_STATE);
        if (enabled != null && !enabled) {
            return null;
        }

        return (Integer) rowDevice.get(DevicesDao.DEV_ID);
    }

    private Map<String, Integer> getContainerLot(Map<String, Object> attrMap) {
        Map<String, Object> filter = Map.of(DevicesDao.DEV_MAC, attrMap.get(DevicesDao.DEV_MAC));
        List<String> columns = List.of(
                ContainersDao.CNT_ID,
                LotsDao.LOT_ID
        );

        EntityResult eR = this.daoHelper.query(measurementsDao, filter, columns, "container_lot");
        if (eR.isEmpty()) {
            return null;
        }

        Map<String, Object> row = eR.getRecordValues(0);
        return Map.of(
                ContainersDao.CNT_ID, (Integer) row.get(ContainersDao.CNT_ID),
                LotsDao.LOT_ID, (Integer) row.get(LotsDao.LOT_ID)
        );
    }

    private boolean canInsertMeasurement(Integer devId, int persistenceMinutes) {
        List<String> columns = List.of(
                DevicesDao.DEV_ID,
                DevicesDao.DEV_MAC,
                DevicesDao.DEV_PERSISTENCE,
                DevicesDao.DEV_STATE
        );
        EntityResult lastTimeResult = devicesService.lastTimeWithoutCMP(
                Map.of(DevicesDao.DEV_ID, devId),
                columns
        );

        if (lastTimeResult.isEmpty() || lastTimeResult.isWrong()) {
            return true;
        }

        // Obtener el valor de la última medición (me_date)
        Timestamp lastDate = (Timestamp) lastTimeResult.getRecordValues(0).get(MeasurementsDao.ME_DATE);

        // Comprobar si han pasado más de los minutos configurados
        long currentTimeMillis = System.currentTimeMillis();
        long lastTimeMillis = lastDate.getTime();
        long persistenceMillis = persistenceMinutes * 60000L;

        return (currentTimeMillis - lastTimeMillis) > persistenceMillis;
    }

    private Integer getPersistenceMinutes(Integer devId) {
        EntityResult result = devicesService.devicesQuery(
                Map.of(DevicesDao.DEV_ID, devId),
                List.of(DevicesDao.DEV_PERSISTENCE)
        );
        if (result.isEmpty() || result.isWrong()) {
            return null;
        }
        return (Integer) result.getRecordValues(0).get(DevicesDao.DEV_PERSISTENCE);
    }

    private void updateMeasurementAlert(EntityResult entityResult, Integer altId) {
        Integer lastMeasurementId = (Integer) entityResult.get(MeasurementsDao.ME_ID);

        Map<String, Object> valuesToUpdate = Map.of(MeasurementsDao.ALT_ID, altId);
        Map<String, Object> filterToUpdate = Map.of(MeasurementsDao.ME_ID, lastMeasurementId);

        measurementsUpdate(valuesToUpdate, filterToUpdate);
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