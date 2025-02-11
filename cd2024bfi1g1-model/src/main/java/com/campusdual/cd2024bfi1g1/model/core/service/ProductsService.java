package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.IProductsService;
import com.campusdual.cd2024bfi1g1.model.core.dao.DevicesDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.ProductsDao;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.NullValue;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProductsService")
@Lazy
public class ProductsService implements IProductsService {
    @Autowired
    private ProductsDao productsDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private UserDao userDao;

    @Override
    public EntityResult productsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.productsDao, keyMap, attrList);
    }
    @Override
    public AdvancedEntityResult productsPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int pagesize, int offset, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.productsDao, keysValues, attributes, pagesize, offset, orderBy);
    }

    @Override
    public EntityResult productsInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        Object PROId = attrMap.get(ProductsDao.PRO_ID);

        if (!attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && !attrMap.containsKey(ProductsDao.PRO_MAX_TEMP)) {
            return this.daoHelper.insert(this.productsDao, attrMap);
        }

        if ( attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && attrMap.get(ProductsDao.PRO_MIN_TEMP) instanceof NullValue && attrMap.containsKey(ProductsDao.PRO_MAX_TEMP) && attrMap.get(ProductsDao.PRO_MAX_TEMP) instanceof NullValue) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("MEASUREMENTS_INSERT_ERROR");
            return res;
        }

        if (attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && attrMap.get(ProductsDao.PRO_MIN_TEMP) instanceof NullValue) {

            double maxTemp = getMaxTempForProId(PROId);
            if (Double.isNaN(maxTemp)) {
                if (!attrMap.containsKey(ProductsDao.PRO_MAX_TEMP)) {
                    EntityResult res = new EntityResultMapImpl();
                    res.setCode(EntityResult.OPERATION_WRONG);
                    res.setMessage("MEASUREMENTS_INSERT_ERROR");
                    return res;
                }
            }

            return this.daoHelper.insert(this.productsDao, attrMap);
        }

        if (attrMap.containsKey(ProductsDao.PRO_MAX_TEMP) && attrMap.get(ProductsDao.PRO_MAX_TEMP) instanceof NullValue) {

            double minTemp = getMinTempForProId(PROId);
            if (Double.isNaN(minTemp)) {
                if (!attrMap.containsKey(ProductsDao.PRO_MIN_TEMP)) {
                    EntityResult res = new EntityResultMapImpl();
                    res.setCode(EntityResult.OPERATION_WRONG);
                    res.setMessage("MEASUREMENTS_INSERT_ERROR");
                    return res;
                }
            }
            return this.daoHelper.insert(this.productsDao, attrMap);
        }

        if (attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && !attrMap.containsKey(ProductsDao.PRO_MAX_TEMP)) {
            Object minTemp = attrMap.get(ProductsDao.PRO_MIN_TEMP);
            if (minTemp != null) {
                double maxTemp = getMaxTempForProId(PROId);
                if (!Double.isNaN(maxTemp)) {
                    attrMap.put(ProductsDao.PRO_MAX_TEMP, maxTemp);
                }
            }
        } else if (attrMap.containsKey(ProductsDao.PRO_MAX_TEMP) && !attrMap.containsKey(ProductsDao.PRO_MIN_TEMP)) {
            Object maxTemp = attrMap.get(ProductsDao.PRO_MAX_TEMP);
            if (maxTemp != null) {
                double minTemp = getMinTempForProId(PROId);
                if (!Double.isNaN(minTemp)) {
                    attrMap.put(ProductsDao.PRO_MIN_TEMP, minTemp);
                }
            }
        }
        EntityResult res = validarCamposTemp(attrMap);
        if (res != null) return res;


        return this.daoHelper.insert(this.productsDao, attrMap);
    }

    @Override
    public EntityResult productsUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        Object PROId = keyMap.get(ProductsDao.PRO_ID);

        if (!attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && !attrMap.containsKey(ProductsDao.PRO_MAX_TEMP)) {
            return this.daoHelper.update(this.productsDao, attrMap, keyMap);
        }

        if ( attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && attrMap.get(ProductsDao.PRO_MIN_TEMP) instanceof NullValue && attrMap.containsKey(ProductsDao.PRO_MAX_TEMP) && attrMap.get(ProductsDao.PRO_MAX_TEMP) instanceof NullValue) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("MEASUREMENTS_INSERT_ERROR");
            return res;
        }

        if (attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && attrMap.get(ProductsDao.PRO_MIN_TEMP) instanceof NullValue) {

            double maxTemp = getMaxTempForProId(PROId);
            if (Double.isNaN(maxTemp)) {
                if (!attrMap.containsKey(ProductsDao.PRO_MAX_TEMP)) {
                    EntityResult res = new EntityResultMapImpl();
                    res.setCode(EntityResult.OPERATION_WRONG);
                    res.setMessage("MEASUREMENTS_INSERT_ERROR");
                    return res;
                }
            }

            return this.daoHelper.update(this.productsDao, attrMap, keyMap);
        }

        if (attrMap.containsKey(ProductsDao.PRO_MAX_TEMP) && attrMap.get(ProductsDao.PRO_MAX_TEMP) instanceof NullValue) {

            double minTemp = getMinTempForProId(PROId);
            if (Double.isNaN(minTemp)) {
                if (!attrMap.containsKey(ProductsDao.PRO_MIN_TEMP)) {
                    EntityResult res = new EntityResultMapImpl();
                    res.setCode(EntityResult.OPERATION_WRONG);
                    res.setMessage("MEASUREMENTS_INSERT_ERROR");
                    return res;
                }
            }
            return this.daoHelper.update(this.productsDao, attrMap, keyMap);
        }

        if (attrMap.containsKey(ProductsDao.PRO_MIN_TEMP) && !attrMap.containsKey(ProductsDao.PRO_MAX_TEMP)) {
            Object minTemp = attrMap.get(ProductsDao.PRO_MIN_TEMP);
            if (minTemp != null) {
                double maxTemp = getMaxTempForProId(PROId);
                if (!Double.isNaN(maxTemp)) {
                    attrMap.put(ProductsDao.PRO_MAX_TEMP, maxTemp);
                }
            }
        } else if (attrMap.containsKey(ProductsDao.PRO_MAX_TEMP) && !attrMap.containsKey(ProductsDao.PRO_MIN_TEMP)) {
            Object maxTemp = attrMap.get(ProductsDao.PRO_MAX_TEMP);
            if (maxTemp != null) {
                double minTemp = getMinTempForProId(PROId);
                if (!Double.isNaN(minTemp)) {
                    attrMap.put(ProductsDao.PRO_MIN_TEMP, minTemp);
                }
            }
        }
        EntityResult res = validarCamposTemp(attrMap);
        if (res != null) return res;

        return this.daoHelper.update(this.productsDao, attrMap, keyMap);
    }

    @Override
    public EntityResult productsDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.productsDao, keyMap);
    }

    private EntityResult validarCamposTemp(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Object minTemp = attrMap.get(ProductsDao.PRO_MIN_TEMP);
        Object maxTemp = attrMap.get(ProductsDao.PRO_MAX_TEMP);

        if (minTemp == null && maxTemp == null) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            res.setMessage("MEASUREMENTS_INSERT_ERROR");
            return res;
        }

        if (minTemp != null && maxTemp != null) {
            try {
                Float minTempValue = Float.parseFloat(minTemp.toString());
                Float maxTempValue = Float.parseFloat(maxTemp.toString());

                if (minTempValue >= maxTempValue) {
                    EntityResult res = new EntityResultMapImpl();
                    res.setCode(EntityResult.OPERATION_WRONG);
                    res.setMessage("MEASUREMENTS_INVALID_ERROR");
                    return res;
                }
            } catch (NumberFormatException e) {
                EntityResult res = new EntityResultMapImpl();
                res.setCode(EntityResult.OPERATION_WRONG);
                res.setMessage("MEASUREMENTS_DATA_ERROR");
                return res;
            }
        }
        return null;
    }

    public double getMaxTempForProId(Object proId) {
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(ProductsDao.PRO_ID, proId);

        List<String> attrList = new ArrayList<>();
        attrList.add(ProductsDao.PRO_MAX_TEMP);

        EntityResult result = this.daoHelper.query(this.productsDao, keyMap, attrList, "get_max_temp_pro_Id");

        if (result != null && result.calculateRecordNumber() > 0) {
            Object maxTempValue = result.get(ProductsDao.PRO_MAX_TEMP);

            if (maxTempValue instanceof List) {
                List<?> tempList = (List<?>) maxTempValue;
                if (!tempList.isEmpty()) {
                    Object firstValue = tempList.get(0);
                    if (firstValue instanceof Number) {
                        return ((Number) firstValue).doubleValue();
                    }
                }
            }
        }

        return Double.NaN;
    }

    public double getMinTempForProId(Object proId) {
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(ProductsDao.PRO_ID, proId);

        List<String> attrList = new ArrayList<>();
        attrList.add(ProductsDao.PRO_MIN_TEMP);

        EntityResult result = this.daoHelper.query(this.productsDao, keyMap, attrList, "get_min_temp_pro_Id");

        if (result != null && result.calculateRecordNumber() > 0) {
            Object minTempValue = result.get(ProductsDao.PRO_MIN_TEMP);

            if (minTempValue instanceof List) {
                List<?> tempList = (List<?>) minTempValue;
                if (!tempList.isEmpty()) {
                    Object firstValue = tempList.get(0);
                    if (firstValue instanceof Number) {
                        return ((Number) firstValue).doubleValue();
                    }
                }
            }
        }

        return Double.NaN;
    }
}
