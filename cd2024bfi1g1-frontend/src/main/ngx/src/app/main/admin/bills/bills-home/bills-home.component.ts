import { Component } from '@angular/core';
import { Expression, FilterExpressionUtils, Util } from "ontimize-web-ngx";

@Component({
  selector: 'app-bills-home',
  templateUrl: './bills-home.component.html',
  styleUrls: ['./bills-home.component.css']
})
export class BillsHomeComponent {
  createFilter(values: Array<{ attr: string; value: any }>): Expression {
    let filters: Array<Expression> = [];
    values.forEach(fil => {
      if (Util.isDefined(fil.value)) {
        const attributeMapping = {
          year: "BIL_YEAR",
          month: "BIL_MONTH",
          company: "CMP_NAME"
        };

        const fieldName = attributeMapping[fil.attr];
        if (fieldName && fil.value) {
          const valueAsString = String(fil.value);
          filters.push(FilterExpressionUtils.buildExpressionLike(fieldName, valueAsString));
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
