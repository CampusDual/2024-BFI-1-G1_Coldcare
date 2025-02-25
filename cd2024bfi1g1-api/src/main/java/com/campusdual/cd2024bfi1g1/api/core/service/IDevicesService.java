package com.campusdual.cd2024bfi1g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IDevicesService {

    EntityResult devicesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult devicesWithMeasurementCountQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult devicesWithoutUserQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult devicesInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    EntityResult devicesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult lastTimeQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult devicesDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult containerLotQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
}
