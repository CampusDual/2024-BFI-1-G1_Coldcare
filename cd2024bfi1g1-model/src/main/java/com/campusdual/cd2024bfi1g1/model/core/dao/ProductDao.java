package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("ProductDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/ProductDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class ProductDao extends OntimizeJdbcDaoSupport {
    public static final String PRO_ID = "PRO_ID";
    public static final String PRO_NAME = "PRO_NAME";
    public static final String PRO_MIN_TEMP = "PRO_MIN_TEMP";
    public static final String PRO_MAX_TEMP = "PRO_MAX_TEMP";
    public static final List<String> COLUMNS = Arrays.asList(PRO_ID, PRO_NAME,PRO_MIN_TEMP,PRO_MAX_TEMP);
}