package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IAlertsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.AlertsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserFirebaseTokenDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AlertsService")
@Lazy

public class AlertsService implements IAlertsService {
    @Autowired
    private AlertsDao alertsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserFirebaseTokenService userFirebaseTokenService;
    @Autowired
    private NotificationService notificationService;


    @Override
    public EntityResult alertsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.alertsDao, keyMap, attrList);
    }

    @Override
    public EntityResult containersAlertsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.alertsDao, keyMap, attrList, "containersAlerts");
    }

    @Override
    public EntityResult alertsWithCalculatedColumnsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.alertsDao, keyMap, attrList, "alertsWithCalculatedColumns");
    }


    @Override
    public AdvancedEntityResult alertsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.alertsDao, keysValues, attributes, pagesize, offset, orderBy, "alertsWithContainersLots");
    }

    @Override
    public EntityResult alertsInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        EntityResult result = this.daoHelper.insert(this.alertsDao, attrMap);

        if (result.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
            EntityResult alertResult = this.daoHelper.query(this.alertsDao, attrMap, List.of("CMP_ID"), "containersAlerts");

            if (alertResult.getCode() == EntityResult.OPERATION_SUCCESSFUL && alertResult.calculateRecordNumber() > 0) {
                Integer cmpId = (Integer) alertResult.getRecordValues(0).get("CMP_ID");

                if (cmpId != null) {
                    Map<String, Object> filter = new HashMap<>();
                    filter.put("CMP_ID", cmpId);

                    EntityResult tokensResult = userFirebaseTokenService.userFirebaseTokenQuery(filter, List.of(UserFirebaseTokenDao.UFT_TOKEN));

                    if (tokensResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
                        Object rawData = tokensResult.get(UserFirebaseTokenDao.UFT_TOKEN);
                        List<String> tokensList = new ArrayList<>();

                        if (rawData instanceof List) {
                            for (Object entry : (List<?>) rawData) {
                                tokensList.add((String) entry);
                            }
                        } else {
                            System.out.println("Formato inesperado de datos para tokens: " + rawData);
                        }

                        for (String token : tokensList) {
                            String response = notificationService.sendNotification(token, "Coldcare", "Se ha producido una alerta");
                            System.out.println(response);
                        }
                    } else {
                        System.out.println("Error al obtener los tokens. Código de error: " + tokensResult.getCode());
                    }
                } else {
                    System.out.println("No se encontró el CMP_ID para la alerta.");
                }
            } else {
                System.out.println("Error al obtener la empresa de la alerta.");
            }
        } else {
            System.out.println("Error al insertar la alerta. Código de error: " + result.getCode());
        }

        return result;
    }


    @Override
    public EntityResult alertsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.alertsDao, attrMap, keyMap);
    }
    @Override
    public EntityResult  alertsWithCalculatedColumnsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.alertsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult alertsDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.alertsDao, keyMap);
    }
}
