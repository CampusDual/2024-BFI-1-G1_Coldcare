package com.campusdual.cd2024bfi1g1.model.core.dao;

import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
@Repository("VehiclesDao")

public class VehiclesDao extends OntimizeJdbcDaoSupport {

    public static final String VHC_ID = "VHC_ID";
    public static final String VHC_PLATE = "VHC_PLATE";
    public static final String CMP_ID = "CMP_ID";
    public static final List<String> COLUMNS = Arrays.asList(CMP_ID, VHC_ID, VHC_PLATE);
}
