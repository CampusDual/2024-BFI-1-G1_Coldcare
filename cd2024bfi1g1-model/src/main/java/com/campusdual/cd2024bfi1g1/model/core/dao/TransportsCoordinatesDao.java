package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("TransportsCoordinatesDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/TransportsCoordinatesDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class TransportsCoordinatesDao extends OntimizeJdbcDaoSupport {

    public static final String TC_ID = "TC_ID";
    public static final String TC_LATITUDE = "TC_LATITUDE";
    public static final String TC_LONGITUDE = "TC_LONGITUDE";
    public static final String TC_DATE = "TC_DATE";
    public static final String TRP_ID = "TRP_ID";
    public static final String TC_HAS_ALERT = "TC_HAS_ALERT";

    public static final List<String> COLUMNS = Arrays.asList(TC_ID, TC_LATITUDE, TC_LONGITUDE, TC_DATE, TRP_ID, TC_HAS_ALERT);

}
