package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository("VehiclesDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/VehiclesDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class VehiclesDao extends OntimizeJdbcDaoSupport {

    public static final String VHC_ID = "VHC_ID";
    public static final String VHC_PLATE = "VHC_PLATE";
    public static final String CMP_ID = "CMP_ID";
    public static final String USR_ID = "USR_ID";
    public static final List<String> COLUMNS = Arrays.asList(VHC_ID, VHC_PLATE, CMP_ID, USR_ID);
}
