package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("TransportsDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/TransportsDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class TransportsDao extends OntimizeJdbcDaoSupport {

    public static final String TRP_ID = "TRP_ID";
    public static final String TRP_ORIGIN = "TRP_ORIGIN";
    public static final String TRP_DESTINATION = "TRP_DESTINATION";
    public static final String CNT_ID = "CNT_ID";
    public static final String CMP_ID = "CMP_ID";
    public static final String VHC_ID = "VHC_ID";
    public static final String TRP_DATE = "TRP_DATE";
    public static final String USR_ID = "USR_ID";
    public static final String TST_ID = "TST_ID";

    public static final List<String> COLUMNS = Arrays.asList(TRP_ID, TRP_ORIGIN, TRP_DESTINATION, CNT_ID, CMP_ID, VHC_ID, TRP_DATE, USR_ID, TST_ID);
}
