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
    public static final String PP_ID = "PP_ID";
    public static final String PP_FIXED_PRICE = "PP_FIXED_PRICE";
    public static final String PP_DEV_PRICE="PP_DEV_PRICE";
    public static final String PP_BUNDLE_PRICE="PP_BUNDLE_PRICE";
    public static final String PP_BUNDLE_REQUESTS="PP_BUNDLE_REQUESTS";
    public static final String PP_START="PP_START";
    public static final String PP_END="PP_END";
    public static final String PLN_ID="PLN_ID";
    public static final List<String> COLUMNS = Arrays.asList(PP_ID,PP_FIXED_PRICE,PP_DEV_PRICE,PP_BUNDLE_PRICE,
            PP_BUNDLE_REQUESTS,PP_START,PP_END, PLN_ID);
}
