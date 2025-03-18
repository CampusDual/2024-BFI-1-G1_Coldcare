package com.campusdual.cd2024bfi1g1.api.core.service;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import java.util.List;
import java.util.Map;

public interface IPlanService {
    EntityResult planQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult planInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    EntityResult planUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult planDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult planwithpricesQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
}