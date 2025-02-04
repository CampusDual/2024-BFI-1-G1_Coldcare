package com.campusdual.cd2024bfi1g1.model.core.dao;

import org.springframework.context.annotation.Lazy;
import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("MeasurementsDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/MeasurementsDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class MeasurementsDao extends OntimizeJdbcDaoSupport{

    public static final String ME_ID = "ME_ID";
    public static final String ME_TEMP = "ME_TEMP";
    public static final String ME_HUMIDITY = "ME_HUMIDITY";
    public static final String ME_DATE = "ME_DATE";
    public static final String DEV_ID = "DEV_ID";
    public static final String LOT_ID = "LOT_ID";
    public static final String CNT_ID = "CNT_ID";
    public static final String ALT_ID = "ALT_ID";

    public static final List<String> COLUMNS = Arrays.asList(ME_ID, ME_TEMP, ME_HUMIDITY, ME_DATE, DEV_ID, LOT_ID, CNT_ID, ALT_ID);
}
