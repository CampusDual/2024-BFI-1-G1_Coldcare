package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository("PlanDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/PlanDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class PlanDao extends OntimizeJdbcDaoSupport {

    public static final String PLN_ID = "PLN_ID";
    public static final String PLN_NAME="PLN_NAME";
    public static final List<String> COLUMNS = Arrays.asList(PLN_ID, PLN_NAME);
}