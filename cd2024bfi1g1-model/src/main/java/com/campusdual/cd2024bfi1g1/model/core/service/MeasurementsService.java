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
    private LotsDao lotsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private DevicesService devicesService;
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
        Integer devId = getOrCreateDeviceId(attrMap);
        if (devId == null) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "Device not found");
        }

        attrMap.put(MeasurementsDao.DEV_ID, devId);

        Map<String, Object> deviceInfo = getDeviceInfo(devId);
        if (deviceInfo == null) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "Error querying device info");
        }

        Integer cntId = (Integer) deviceInfo.get(ContainersDao.CNT_ID);
        Integer lotId = (Integer) deviceInfo.get(MeasurementsDao.LOT_ID);
        if (cntId != null) {
            attrMap.put(MeasurementsDao.CNT_ID, cntId);
        }
        if (lotId != null) {
            attrMap.put(MeasurementsDao.LOT_ID, lotId);
        }

        Integer persistenceMinutes = (Integer) deviceInfo.get(DevicesDao.DEV_PERSISTENCE);
        if (persistenceMinutes == null) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT, "Error querying device persistence");
        }
        Boolean devState = (Boolean) deviceInfo.get(DevicesDao.DEV_STATE);

        if (!canInsertMeasurement(devId, persistenceMinutes, devState)) {
            return new EntityResultMapImpl(EntityResult.OPERATION_WRONG, EntityResult.NODATA_RESULT,
                    "Wait more than " + persistenceMinutes + " minutes to insert new measurement");
        }

        Double currentTemp = (Double) attrMap.get(MeasurementsDao.ME_TEMP);

        if (lotId == null) {
            return daoHelper.insert(measurementsDao, attrMap);
        }

        Integer altId = computeMeasurementAlert(currentTemp, devId, lotId, cntId);
        if (altId != null) {
            attrMap.put(AlertsDao.ALT_ID, altId);
        }

        return daoHelper.insert(measurementsDao, attrMap);
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

    private Integer getOrCreateDeviceId(Map<String, Object> attrMap) {
        Map<String, Object> filter = Map.of(DevicesDao.DEV_MAC, attrMap.get(DevicesDao.DEV_MAC));
        List<String> columns = List.of(
                DevicesDao.DEV_ID,
                DevicesDao.DEV_MAC,
                DevicesDao.DEV_PERSISTENCE,
                DevicesDao.DEV_STATE
        );

        EntityResult query = devicesService.devicesQuery(filter, columns);
        if (query.isWrong()) {
            return null;
        }
        if (query.isEmpty()) {
            EntityResult eR = devicesService.devicesInsert(filter);
            return (Integer) eR.get(DevicesDao.DEV_ID);
        }

        Map<String, Object> rowDevice = query.getRecordValues(0);
        Boolean enabled = (Boolean) rowDevice.get(DevicesDao.DEV_STATE);
        if (enabled != null && !enabled) {
            return null;
        }

        return (Integer) rowDevice.get(DevicesDao.DEV_ID);
    }

    private Map<String, Object> getDeviceInfo(Integer devId) {
        Map<String, Object> filter = Map.of(DevicesDao.DEV_ID, devId);
        List<String> columns = List.of(
                ContainersDao.CNT_ID,
                LotsDao.LOT_ID,
                DevicesDao.DEV_PERSISTENCE,
                DevicesDao.DEV_STATE
        );

        EntityResult eR = this.daoHelper.query(measurementsDao, filter, columns, "container_lot");
        if (eR.isEmpty() || eR.isWrong()) {
            return null;
        }

        Map<String, Object> row = eR.getRecordValues(0);
        Integer cntId = (Integer) row.get(ContainersDao.CNT_ID);
        Integer lotId = (Integer) row.get(LotsDao.LOT_ID);
        Integer devPersistence = (Integer) row.get(DevicesDao.DEV_PERSISTENCE);
        Boolean devState = (Boolean) row.get(DevicesDao.DEV_STATE);

        if (cntId == null) {
            return Map.of(
                    DevicesDao.DEV_PERSISTENCE, devPersistence,
                    DevicesDao.DEV_STATE, devState
            );
        }

        if (lotId == null) {
            return Map.of(
                    ContainersDao.CNT_ID, cntId,
                    DevicesDao.DEV_PERSISTENCE, devPersistence,
                    DevicesDao.DEV_STATE, devState
            );
        }

        return Map.of(
                ContainersDao.CNT_ID, cntId,
                LotsDao.LOT_ID, lotId,
                DevicesDao.DEV_PERSISTENCE, devPersistence,
                DevicesDao.DEV_STATE, devState
        );
    }

    private boolean canInsertMeasurement(Integer devId, Integer persistenceMinutes, Boolean devState) {
        Map<String, Object> measurementInfo = getLastRecordedMeasurement(devId);
        if (measurementInfo == null) {
            return true;
        }
        Timestamp lastDate = (Timestamp) measurementInfo.get(MeasurementsDao.ME_DATE);

        // Comprobar si han pasado más de los minutos configurados
        long currentTimeMillis = System.currentTimeMillis();
        long lastTimeMillis = lastDate.getTime();
        long persistenceMillis = persistenceMinutes * 60000L;

        return (currentTimeMillis - lastTimeMillis) > persistenceMillis;
    }

    private Integer computeMeasurementAlert(Double currentTemp, Integer devId, Integer lotId, Integer cntId) {
        Map<String, Object> measurementInfo = getLastRecordedMeasurement(devId);
        if (measurementInfo == null) {
            return null;
        }
        Double lastTemp = (Double) measurementInfo.get(MeasurementsDao.ME_TEMP);
        if (lastTemp == null) {
            return null;
        }
        Map<String, Object> lotMinMaxTemp = getLotMinMaxTemperature(lotId);
        if (lotMinMaxTemp == null) {
            return null;
        }

        Double lotMinTemp = (Double) lotMinMaxTemp.get(LotsDao.MIN_TEMP);
        Double lotMaxTemp = (Double) lotMinMaxTemp.get(LotsDao.MAX_TEMP);

        boolean isError;

        Integer altId = (Integer) measurementInfo.get(MeasurementsDao.ALT_ID);

        if (measurementInfo.get(MeasurementsDao.ALT_ID) != null) {
            Map<String, Object> filter = Map.of(
                    AlertsDao.ALT_ID, altId
            );
            List<String> columns = List.of(
                    AlertsDao.ALT_MIN_TEMP,
                    AlertsDao.ALT_MAX_TEMP
            );

            EntityResult eR = alertsService.alertsQuery(filter, columns);
            if (eR.isEmpty() || eR.isWrong()) {
                return null;
            }

            Double altMinTemp = (Double) eR.getRecordValues(0).get(AlertsDao.ALT_MIN_TEMP);
            Double altMaxTemp = (Double) eR.getRecordValues(0).get(AlertsDao.ALT_MAX_TEMP);

            boolean isRangeChanged = (!altMaxTemp.equals(lotMaxTemp) || !altMinTemp.equals(lotMinTemp));

            if (isRangeChanged) {
                closeLastAlert(cntId, lotId);

                isError = (currentTemp > lotMaxTemp || currentTemp < lotMinTemp);

                if (isError) {
                    createAlert(lotMinTemp, lotMaxTemp, cntId, lotId);
                }

                return isError ? getLastAlertId(cntId, lotId) : null;
            }

            isError = (currentTemp > altMaxTemp || currentTemp < altMinTemp);

            if (!isError) {
                closeLastAlert(cntId, lotId);
                return null;
            }

            return getLastAlertId(cntId, lotId);

        }

        isError = (currentTemp > lotMaxTemp || currentTemp < lotMinTemp);

        if (isError) {
            createAlert(lotMinTemp, lotMaxTemp, cntId, lotId);
        }

        return isError ? getLastAlertId(cntId, lotId) : null;
    }

    private Map<String, Object> getLotMinMaxTemperature(Integer lotId) {
        Map<String, Object> filter = Map.of(LotsDao.LOT_ID, lotId);
        List<String> columns = List.of(
                LotsDao.MIN_TEMP,
                LotsDao.MAX_TEMP
        );

        EntityResult eR = this.daoHelper.query(this.lotsDao, filter, columns);
        if (eR.isEmpty() || eR.isWrong()) {
            return null;
        }

        return Map.of(
                LotsDao.MIN_TEMP, eR.getRecordValues(0).get(LotsDao.MIN_TEMP),
                LotsDao.MAX_TEMP, eR.getRecordValues(0).get(LotsDao.MAX_TEMP)
        );
    }

    private Map<String, Object> getLastRecordedMeasurement(Integer devId) {
        Map<String, Object> filter = Map.of(DevicesDao.DEV_ID, devId);
        List<String> columns = List.of(
                MeasurementsDao.ME_TEMP,
                MeasurementsDao.ME_DATE,
                MeasurementsDao.ALT_ID
        );
        List<SQLStatementBuilder.SQLOrder> orderBy = List.of(
                new SQLStatementBuilder.SQLOrder(MeasurementsDao.ME_DATE, false)
        );

        EntityResult eR = measurementsPaginationQuery(filter, columns, 1, 0, orderBy);
        if (eR.isEmpty() || eR.isWrong()) {
            return null;
        }

        if (eR.getRecordValues(0).get(MeasurementsDao.ALT_ID) == null) {
            return Map.of(
                    MeasurementsDao.ME_TEMP, eR.getRecordValues(0).get(MeasurementsDao.ME_TEMP),
                    MeasurementsDao.ME_DATE, eR.getRecordValues(0).get(MeasurementsDao.ME_DATE)
            );
        }

        return Map.of(
                MeasurementsDao.ME_TEMP, eR.getRecordValues(0).get(MeasurementsDao.ME_TEMP),
                MeasurementsDao.ME_DATE, eR.getRecordValues(0).get(MeasurementsDao.ME_DATE),
                MeasurementsDao.ALT_ID, eR.getRecordValues(0).get(MeasurementsDao.ALT_ID)
        );
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