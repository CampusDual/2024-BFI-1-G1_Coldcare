package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("PricingDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/PricingDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class PricingDao extends OntimizeJdbcDaoSupport {
    public static final String PRC_ID = "PRC_ID";
    public static final String PRC_FPRC = "PRC_FPRC";
    public static final String PRC_DPRC="PRC_DPRC";
    public static final String PRC_BPRC="PRC_BPRC";
    public static final String PRC_BREQ="PRC_BREQ";
    public static final String PRC_START="PRC_START";
    public static final String PRC_END="PRC_END";
    public static final String PLN_ID="PLN_ID";
    public static final List<String> COLUMNS = Arrays.asList(PRC_ID,PRC_FPRC,PRC_DPRC,PRC_BPRC,PRC_BREQ,PRC_START,PRC_END, PLN_ID);
}
