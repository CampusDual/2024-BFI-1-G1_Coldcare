package com.campusdual.cd2024bfi1g1.api.core.service;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IMeasurementsService {

    EntityResult measurementsQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult ContainerLotQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult measurementsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    EntityResult measurementsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    AdvancedEntityResult measurementsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException;
    EntityResult measurementsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}