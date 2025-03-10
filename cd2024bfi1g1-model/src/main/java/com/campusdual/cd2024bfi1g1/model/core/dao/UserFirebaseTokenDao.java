package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository("UserFirebaseTokenDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/UserFirebaseTokenDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")

public class UserFirebaseTokenDao extends OntimizeJdbcDaoSupport {

    public static final String UFT_ID = "UFT_ID";
    public static final String USR_ID = "USR_ID";
    public static final String UFT_TOKEN = "UFT_TOKEN";

    public static final List<String> COLUMNS = Arrays.asList(UFT_ID, USR_ID, UFT_TOKEN);
}