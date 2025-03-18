package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("ContainersLotsDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/ContainersLotsDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class ContainersLotsDao extends OntimizeJdbcDaoSupport {

    public static final String CL_ID = "CL_ID";
    public static final String CNT_ID = "CNT_ID";
    public static final String LOT_ID = "LOT_ID";
    public static final String CL_START_DATE = "CL_START_DATE";
    public static final String CL_END_DATE = "CL_END_DATE";

    public static final List<String> COLUMNS = Arrays.asList(CL_ID, CNT_ID, LOT_ID, CL_START_DATE, CL_END_DATE);

}
