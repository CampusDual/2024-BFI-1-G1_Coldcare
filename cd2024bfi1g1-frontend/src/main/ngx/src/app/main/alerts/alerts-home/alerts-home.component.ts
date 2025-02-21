import { Component } from '@angular/core';
import { Expression, FilterExpressionUtils, Util } from 'ontimize-web-ngx';

@Component({
  selector: 'app-alerts-home',
  templateUrl: './alerts-home.component.html',
  styleUrls: ['./alerts-home.component.css']
})
export class AlertsHomeComponent {

  createFilter(values: Array<{ attr; value }>): Expression {
    // Prepare simple expressions from the filter components values
    let filters: Array<Expression> = [];
    values.forEach(fil => {
      if (Util.isDefined(fil.value)) {
        const attributeMapping = {
          lote: "lot_name",
          contenedor: "cnt_name"
        };

        const fieldName = attributeMapping[fil.attr];
        if (fieldName) {
          filters.push(FilterExpressionUtils.buildExpressionLike(fieldName, fil.value));
        }

      }
    });

    // Build complex expression
    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) =>
        FilterExpressionUtils.buildComplexExpression(
          exp1,
          exp2,
          FilterExpressionUtils.OP_AND
        )
      );
    } else {
      return null;
    }
  }

  getRowClass(row: any): string {
    if (!row.hasOwnProperty('ALT_DATE_END')) {
      return 'row-highlight'; // Se aplica la clase si no tiene ALT_DATE_END o si es null
    }
    return ''; // No se aplica clase si ALT_DATE_END tiene un valor
  }
  

}
