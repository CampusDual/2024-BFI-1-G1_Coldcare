package com.campusdual.cd2024bfi1g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface ITransfersService {
    EntityResult transfersQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult transfersInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    EntityResult transfersUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult transfersDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}

