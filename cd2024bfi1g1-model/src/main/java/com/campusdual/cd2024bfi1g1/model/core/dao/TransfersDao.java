package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("TransfersDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/TransfersDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class TransfersDao extends OntimizeJdbcDaoSupport {
    public static final String TRA_ID = "TRA_ID";
    public static final String TRA_ORIGIN_CL = "TRA_ORIGIN_CL";
    public static final String TRA_DESTINY_CL = "TRA_DESTINY_CL";
    public static final List<String> COLUMNS = Arrays.asList(TRA_ID,TRA_ORIGIN_CL,TRA_DESTINY_CL);
}