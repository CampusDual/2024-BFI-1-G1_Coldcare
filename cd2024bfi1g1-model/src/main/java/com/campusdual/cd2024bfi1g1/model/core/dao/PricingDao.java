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
    public static final String PLANPRICES_ID = "PLANPRICES_ID";
    public static final String PLANPRICES_FIXEDPRICE = "PLANPRICES_FIXEDPRICE";
    public static final String PLANPRICES_DEVPRICE="PLANPRICES_DEVPRICE";
    public static final String PLANPRICES_BUNDLEPRICE="PLANPRICES_BUNDLEPRICE";
    public static final String PLANPRICES_BUNDLEREQUESTS="PLANPRICES_BUNDLEREQUESTS";
    public static final String PLANPRICES_START="PLANPRICES_START";
    public static final String PLANPRICES_END="PLANPRICES_END";
    public static final String PLN_ID="PLN_ID";
    public static final List<String> COLUMNS = Arrays.asList(PLANPRICES_ID,PLANPRICES_FIXEDPRICE,PLANPRICES_DEVPRICE,PLANPRICES_BUNDLEPRICE,
            PLANPRICES_BUNDLEREQUESTS,PLANPRICES_START,PLANPRICES_END, PLN_ID);
}
