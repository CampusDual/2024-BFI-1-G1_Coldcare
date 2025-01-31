package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("BillsDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/BillsDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class BillsDao extends OntimizeJdbcDaoSupport {

    public static final String BIL_ID = "BIL_ID";
    public static final String CMP_ID = "CMP_ID";
    public static final String BIL_DATE = "BIL_DATE";
    public static final String BIL_MEASUREMENTS = "BIL_MEASUREMENTS";
    public static final String BIL_DEVICES = "BIL_DEVICES";
    public static final String BIL_EXPENSE = "BIL_EXPENSE";
    public static final List<String> COLUMNS = Arrays.asList(BIL_ID, CMP_ID, BIL_DATE, BIL_MEASUREMENTS, BIL_DEVICES,BIL_EXPENSE);
}
