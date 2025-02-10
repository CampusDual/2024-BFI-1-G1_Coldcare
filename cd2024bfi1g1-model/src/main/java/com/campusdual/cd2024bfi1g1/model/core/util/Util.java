package com.campusdual.cd2024bfi1g1.model.core.util;

import com.campusdual.cd2024bfi1g1.model.core.dao.ContainersLotsDao;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicField;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.jee.common.services.user.UserInformation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Map;

public class Util {
    public static Integer getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Integer userId = null;

        if (principal instanceof UserInformation) {
            UserInformation userInfo = (UserInformation) principal;

            // Extraer el mapa otherData
            Map<Object, Object> rawOtherData = userInfo.getOtherData();

            userId = (Integer) rawOtherData.get("usr_id");
        }

        return userId;
    }

    /**
     * Crea una expresión de búsqueda basada en un rango de fechas y un identificador opcional.
     * Permite filtrar registros según una fecha de inicio y una fecha de fin, con la opción
     * de excluir un registro específico en caso de realizar una actualización.
     *
     * La fecha de inicio **no debe ser nula ni vacía** en ningún caso.
     * Si otros campos no deben ser considerados en la búsqueda, se pueden pasar como `null` o vacíos.
     * La expresión permite que la fecha de inicio coincida con la fecha de fin de algún registro
     * almacenado en la base de datos.
     *
     * @param startDayKey   Clave del campo que representa la fecha de inicio (obligatorio).
     * @param startDayValue Valor de la fecha de inicio (obligatorio, no nulo ni vacío).
     * @param endDateKey    Clave del campo que representa la fecha de fin (opcional).
     * @param endDateValue  Valor de la fecha de fin (opcional).
     * @param selfIdKey     Clave del identificador del registro a excluir (opcional).
     * @param selfIdValue   Valor del identificador del registro a excluir (opcional).
     * @return Una BasicExpression de búsqueda con las condiciones especificadas y null en caso de que la
     * fecha de inicio y fin estén vacías.
     */
    public static BasicExpression searchBetweenWithDates(String startDayKey, Date startDayValue, String endDateKey, Date endDateValue, String selfIdKey, Integer selfIdValue) {

        if ("".equals(startDayKey) && "".equals(endDateKey)) {
            return null;
        }

        BasicField fieldStart = new BasicField(startDayKey);
        BasicField fieldEnd = new BasicField(endDateKey);

        BasicExpression endDateIsNull = new BasicExpression(fieldEnd, BasicOperator.NULL_OP, null);

        BasicExpression endDateBetween, betweenStartAndEndDate, betweenStartWithoutEnd, bexComplete;
        if (!"".equals(endDateKey) && endDateValue != null) {

            BasicExpression startDateBetween = new BasicExpression(
                    new BasicExpression(fieldStart, BasicOperator.LESS_EQUAL_OP, startDayValue),
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldEnd, BasicOperator.MORE_OP, startDayValue)
            );

            endDateBetween = new BasicExpression(
                    new BasicExpression(fieldStart, BasicOperator.LESS_OP, endDateValue),
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldEnd, BasicOperator.MORE_EQUAL_OP, endDateValue)
            );

            betweenStartAndEndDate = new BasicExpression(
                    new BasicExpression(fieldStart, BasicOperator.MORE_EQUAL_OP, startDayValue),
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldEnd, BasicOperator.LESS_EQUAL_OP, endDateValue)
            );

            betweenStartWithoutEnd = new BasicExpression(
                    endDateIsNull,
                    BasicOperator.AND_OP,
                    new BasicExpression(fieldStart, BasicOperator.LESS_OP, endDateValue)
            );

            bexComplete = new BasicExpression(startDateBetween, BasicOperator.OR_OP, endDateBetween);
            bexComplete = new BasicExpression(bexComplete, BasicOperator.OR_OP, betweenStartAndEndDate);
            bexComplete = new BasicExpression(bexComplete, BasicOperator.OR_OP, betweenStartWithoutEnd);

        } else {
            BasicExpression be1 = new BasicExpression(fieldEnd, BasicOperator.MORE_EQUAL_OP, startDayValue);
            bexComplete = new BasicExpression(endDateIsNull, BasicOperator.OR_OP, be1);
        }

        if (!"".equals(selfIdKey) && selfIdValue != null) {
            BasicField fieldClId = new BasicField(selfIdKey);
            BasicExpression excludeSameId = new BasicExpression(fieldClId, BasicOperator.NOT_EQUAL_OP, selfIdValue);
            bexComplete = new BasicExpression(excludeSameId, BasicOperator.AND_OP, bexComplete);
        }

        return bexComplete;
    }

    /**
     * Valida la relación entre una fecha de inicio y una fecha de fin.
     *
     * La validación considera las siguientes reglas:
     * - Si alguna de las fechas es `null`, se considera válida.
     * - Si las fechas son iguales, se considera válida.
     * - Si la fecha de fin es posterior a la fecha de inicio, se considera válida.
     * - Si la fecha de fin es anterior a la fecha de inicio, se considera inválida.
     *
     * @param startDate Fecha de inicio (puede ser `null`).
     * @param endDate   Fecha de fin (puede ser `null`).
     * @return `true` si la relación entre las fechas es válida, `false` si la fecha de fin es anterior a la fecha de inicio.
     */
    public static boolean validateStartAndEndDates(Date startDate, Date endDate) {

        return startDate == null || endDate == null || startDate.equals(endDate) || endDate.after(startDate);
    }

}
