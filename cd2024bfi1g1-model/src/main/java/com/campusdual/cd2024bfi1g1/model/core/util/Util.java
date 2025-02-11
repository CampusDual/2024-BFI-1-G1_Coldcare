package com.campusdual.cd2024bfi1g1.model.core.util;

import com.ontimize.jee.common.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicField;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
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
     * Creates a {@code BasicExpression} to check if today falls within a date range stored in the database.
     *
     * <p>The date is considered valid if:
     * <ul>
     *   <li>The start date is less than or equal to today.</li>
     *   <li>The end date is either null or greater than or equal to today.</li>
     * </ul>
     *
     * @param startDayKey The key representing the start date field in the database.
     * @param endDateKey  The key representing the end date field in the database.
     * @return A {@code BasicExpression} evaluating whether today's date is within the stored date range.
     */
    public static BasicExpression isDateInCurrentRange (String startDayKey, String endDateKey) {
        BasicField fieldStart = new BasicField(startDayKey);
        BasicField fieldEnd = new BasicField(endDateKey);
        Date currentDate = new Date();

        BasicExpression endDateIsNull = new BasicExpression(fieldEnd, BasicOperator.NULL_OP, null);

        BasicExpression betweenStartWithoutEnd = new BasicExpression(
                endDateIsNull,
                BasicOperator.OR_OP,
                new BasicExpression(fieldEnd, BasicOperator.MORE_EQUAL_OP, currentDate)
        );

        BasicExpression currentDateBetween = new BasicExpression(
                new BasicExpression(fieldStart, BasicOperator.LESS_EQUAL_OP, currentDate),
                BasicOperator.AND_OP,
                betweenStartWithoutEnd
        );

        return currentDateBetween;
    }

    /**
     * Creates a search expression based on a date range and an optional identifier.
     * It allows filtering records according to a start date and an end date, with the option
     * to exclude a specific record when performing an update.
     *
     * The start date **must not be null or empty** under any circumstances.
     * If other fields should not be considered in the search, they can be passed as `null` or empty.
     * The expression allows the start date to match the end date of a record
     * stored in the database.
     *
     * @param startDayKey   Key representing the start date field (mandatory).
     * @param startDayValue Value of the start date (mandatory, not null or empty).
     * @param endDateKey    Key representing the end date field (optional).
     * @param endDateValue  Value of the end date (optional).
     * @param selfIdKey     Key of the identifier of the record to exclude (optional).
     * @param selfIdValue   Value of the identifier of the record to exclude (optional).
     * @return A BasicExpression for search with the specified conditions, or null if both
     *         the start and end dates are empty.
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
            BasicExpression be1 = new BasicExpression(fieldEnd, BasicOperator.MORE_OP, startDayValue);
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
     * Checks the validity of the relationship between a start date and an end date.
     *
     * The validation follows these rules:
     * - If either date is null, it is considered valid.
     * - If both dates are equal, it is considered valid.
     * - If the end date is after the start date, it is considered valid.
     * - If the end date is before the start date, it is considered invalid.
     *
     * @param startDate The start date (can be null).
     * @param endDate   The end date (can be null).
     * @return {@code true} if the date relationship is valid,
     *         {@code false} if the end date is before the start date.
     */
    public static boolean validateStartAndEndDates(Date startDate, Date endDate) {

        return startDate == null || endDate == null || startDate.equals(endDate) || endDate.after(startDate);
    }

    /**
     * Receives an error message code that must be previously stored in the project's .json file.
     *
     * @param msgError Error message stored in the project's .json file.
     * @return An {@code EntityResult} containing the error message.
     */
    public static EntityResult controlErrors(String msgError) {
        EntityResult res = new EntityResultMapImpl();
        res.setCode(EntityResult.OPERATION_WRONG);
        res.setMessage(msgError);
        return res;
    }
}
