package com.campusdual.cd2024bfi1g1.api.core.service;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IAlertsService {
    EntityResult alertsQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    AdvancedEntityResult alertsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException;
    EntityResult alertsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    EntityResult alertsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult alertsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
