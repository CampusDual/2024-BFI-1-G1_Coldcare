import { Component } from '@angular/core';
import { Expression, FilterExpressionUtils } from "ontimize-web-ngx";

@Component({
  selector: 'app-bills-home',
  templateUrl: './bills-home.component.html',
  styleUrls: ['./bills-home.component.css']
})
export class BillsHomeComponent {
  createFilter(values: Array<{ attr: string; value: any }>): Expression {
    let filters: Array<Expression> = [];

    values.forEach((fil) => {
      if (fil.value) {
        switch (fil.attr) {
          case "BIL_YEAR":
            // Filtrar por ID del dispositivo
            filters.push(
              FilterExpressionUtils.buildExpressionEquals("BIL_YEAR", fil.value)
            );
            break;

          case "BIL_MONTH":
            // Filtrar por ID del dispositivo
            filters.push(
              FilterExpressionUtils.buildExpressionEquals("BIL_MONTH", fil.value)
            );
            break;

          case "CMP_NAME":
            // Filtrar por ID del dispositivo
            filters.push(
              FilterExpressionUtils.buildExpressionEquals("CMP_NAME", fil.value)
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
        FilterExpressionUtils.buildComplexExpression(
          exp1,
          exp2,
          FilterExpressionUtils.OP_AND
        )
      );
    } else {
      return null; // Sin filtros aplicados
    }
  }

}
