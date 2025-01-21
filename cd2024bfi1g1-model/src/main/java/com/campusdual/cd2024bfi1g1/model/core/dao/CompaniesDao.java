package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("CompaniesDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/CompaniesDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class CompaniesDao extends OntimizeJdbcDaoSupport {
    public static final String CMP_ID = "CMP_ID";
    public static final String CMP_NAME = "CMP_NAME";

    public static final List<String> COLUMNS = Arrays.asList(CMP_ID, CMP_NAME);
}
