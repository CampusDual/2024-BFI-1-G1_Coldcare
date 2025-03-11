package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.ITransportsCoordinatesService;
import com.campusdual.cd2024bfi1g1.model.core.dao.TransportsCoordinatesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TransportsCoordinatesService")
@Lazy
public class TransportsCoordinatesService implements ITransportsCoordinatesService {

    @Autowired
    private TransportsCoordinatesDao transportsCoordinatesDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult transportsCoordinatesQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.transportsCoordinatesDao, keyMap, attrList);
    }

    @Override
    public EntityResult transportsCoordinatesInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {

        Integer trpId = Integer.parseInt(attrMap.get(TransportsCoordinatesDao.TRP_ID).toString());

        Map<String, Object> filter = Map.of(TransportsCoordinatesDao.TRP_ID, trpId);
        List<String> columns = List.of(TransportsCoordinatesDao.TC_HAS_ALERT);

        Boolean has_alert = this.transportCoordinatesHasAlert(filter, columns);

        if (has_alert) {
            attrMap.put(TransportsCoordinatesDao.TC_HAS_ALERT, true);
        }

        return this.daoHelper.insert(this.transportsCoordinatesDao, attrMap);
    }

    @Override
    public EntityResult transportsCoordinatesUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.transportsCoordinatesDao, attrMap, keyMap);
    }

    @Override
    public EntityResult transportsCoordinatesDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.transportsCoordinatesDao, keyMap);
    }

    private Boolean transportCoordinatesHasAlert(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        EntityResult eR = this.daoHelper.query(this.transportsCoordinatesDao, keyMap, attrList, "coordinates_has_alert");
        if (eR.isEmpty() || eR.isWrong()) {
            return false;
        }
        Map<String, Object> row = eR.getRecordValues(0);

        return (Boolean) row.get(TransportsCoordinatesDao.TC_HAS_ALERT);
    }

}
