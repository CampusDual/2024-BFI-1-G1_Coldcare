package com.campusdual.cd2024bfi1g1.model.core.util;

import com.ontimize.jee.common.services.user.UserInformation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

public class Util {
    public static Integer getId(String column) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Integer id = null;

        if (principal instanceof UserInformation) {
            UserInformation userInfo = (UserInformation) principal;

            // Extraer el mapa otherData
            Map<Object, Object> rawOtherData = userInfo.getOtherData();

            id = (Integer) rawOtherData.get(column);
        }

        return id;
    }
}
