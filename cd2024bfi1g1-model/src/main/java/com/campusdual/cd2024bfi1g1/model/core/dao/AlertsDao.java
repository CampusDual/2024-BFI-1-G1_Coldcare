package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("AlertsDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/AlertsDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class AlertsDao extends OntimizeJdbcDaoSupport {
    public static final String ALT_ID = "ALT_ID";
    public static final String ALT_DATE_INIT = "ALT_DATE_INIT";
    public static final String ALT_DATE_END = "ALT_DATE_END";
    public static final String ALT_MIN_TEMP = "ALT_MIN_TEMP";
    public static final String ALT_MAX_TEMP = "ALT_MAX_TEMP";
    public static final String LOT_ID = "LOT_ID";
    public static final String CNT_ID = "CNT_ID";
    public static final String DEV_ID = "DEV_ID";

    public static final List<String> COLUMNS = Arrays.asList(ALT_ID, ALT_DATE_INIT, ALT_DATE_END, ALT_MIN_TEMP, ALT_MAX_TEMP, LOT_ID, CNT_ID, DEV_ID);
}
