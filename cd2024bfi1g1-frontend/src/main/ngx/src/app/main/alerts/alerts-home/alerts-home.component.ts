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

  editionStarted(arg: any) {
    console.log('editionStarted');
    console.log(arg);
  }

  editionCancelled(arg: any) {
    console.log('editionCancelled');
    console.log(arg);
  }

  editionCommitted(arg: any) {
    console.log('editionCommitted');
    console.log(arg);
  }


}
