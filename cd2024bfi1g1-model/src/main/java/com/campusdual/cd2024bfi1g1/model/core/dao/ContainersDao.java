package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("ContainersDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/ContainersDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class ContainersDao extends OntimizeJdbcDaoSupport {

    public static final String CNT_ID = "CNT_ID";
    public static final String CNT_NAME = "CNT_NAME";
    public static final String CMP_ID = "CMP_ID";
    public static final String LOT_ID = "LOT_ID";
    public static final String CNT_MOBILITY = "CNT_MOBILITY";

    public static final List<String> COLUMNS = Arrays.asList(CNT_ID, CNT_NAME, CMP_ID, LOT_ID, CNT_MOBILITY);
}
