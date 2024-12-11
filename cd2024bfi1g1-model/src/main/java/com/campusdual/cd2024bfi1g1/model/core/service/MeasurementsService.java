package com.campusdual.cd2024bfi1g1.model.core.service;




import com.campusdual.cd2024bfi1g1.api.core.service.IMeasurementsService;

import com.campusdual.cd2024bfi1g1.model.core.dao.MeasurementsDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.dao.IOntimizeDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("DevicesService")
@Lazy
public class MeasurementsService implements IMeasurementsService {

    @Autowired
    private MeasurementsDao MeasurementsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult measurementsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query((IOntimizeDaoSupport) this.MeasurementsDao, keyMap, attrList);
    }

    @Override
    public EntityResult measurementsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
            return this.daoHelper.insert((IOntimizeDaoSupport) this.MeasurementsDao, attrMap);
    }

    @Override
    public EntityResult measurementsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update((IOntimizeDaoSupport) this.MeasurementsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult measurementsDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete((IOntimizeDaoSupport) this.MeasurementsDao, keyMap);
    }

}