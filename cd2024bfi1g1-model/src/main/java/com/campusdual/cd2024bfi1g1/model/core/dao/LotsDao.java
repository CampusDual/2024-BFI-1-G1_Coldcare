package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("LotsDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/LotsDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class LotsDao extends OntimizeJdbcDaoSupport {

    public static final String LOT_ID = "LOT_ID";
    public static final String LOT_NAME = "LOT_NAME";
    public static final String USR_ID = "USR_ID";
    public static final String DEV_NAME = "DEV_NAME";

    public static final List<String> COLUMNS = Arrays.asList(LOT_ID, LOT_NAME, USR_ID, DEV_NAME);
}