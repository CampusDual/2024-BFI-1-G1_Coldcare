import { Component } from "@angular/core";
import { Expression, FilterExpressionUtils } from "ontimize-web-ngx";

@Component({
  selector: "app-medidas-home",
  templateUrl: "./medidas-home.component.html",
  styleUrls: ["./medidas-home.component.css"],
})
export class MedidasAdminComponent {

  colorScheme = {
    domain: ["#1464A5", "#eeeeee", "#c5c5c5"],
  };

  constructor() {}
  
  createFilter(values: Array<{ attr: string; value: any }>): Expression {
    let filters: Array<Expression> = [];

    values.forEach((fil) => {
      if (fil.value) {
        switch (fil.attr) {
          case "DEV_ID":
            // Filtrar por ID del dispositivo
            filters.push(FilterExpressionUtils.buildExpressionEquals("DEV_ID", fil.value));
            break;

          case "ME_DATE":
            // Filtrar por rango de fechas
            if (fil.value.startDate) {
              filters.push(
                FilterExpressionUtils.buildExpressionMoreEqual(
                  "ME_DATE",
                  fil.value.startDate._d.getTime()
                )
              );
            }
            if (fil.value.endDate) {
              filters.push(
                FilterExpressionUtils.buildExpressionLessEqual(
                  "ME_DATE",
                  fil.value.endDate._d.getTime()
                )
              );
            }
            break;

          case "DEV_NAME":
            // Filtrar por nombre del dispositivo (insensible a mayúsculas/minúsculas)
            filters.push(
              FilterExpressionUtils.buildExpressionLike(
                "DEV_NAME",
                `%${fil.value}%`
              )
            );
            break;

          default:
            console.warn(`Atributo desconocido: ${fil.attr}`);
            break;
        }
      }
    });

    // Combinar los filtros con el operador AND si hay más de uno
    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) =>
        FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND)
      );
    } else {
      return null; // Sin filtros aplicados
    }
  }

}
