package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("TransportersDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/TransportersDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class TransportersDao extends OntimizeJdbcDaoSupport {
    public static final String LOC_ID = "LOC_ID";
    public static final String LOC_NAME = "LOC_NAME";
    public static final String LOC_ADDRESS = "LOC_ADDRESS";
    public static final String CMP_ID = "CMP_ID";

    public static final List<String> COLUMNS = Arrays.asList(LOC_ID, LOC_NAME, LOC_ADDRESS, CMP_ID);
}
