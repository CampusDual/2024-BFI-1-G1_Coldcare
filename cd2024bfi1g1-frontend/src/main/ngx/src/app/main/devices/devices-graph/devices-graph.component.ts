import { Component } from '@angular/core';
import { Expression, FilterExpressionUtils } from 'ontimize-web-ngx';
import { LineChartConfiguration } from 'ontimize-web-ngx-charts';

@Component({
  selector: 'app-devices-graph',
  templateUrl: './devices-graph.component.html',
  styleUrls: ['./devices-graph.component.css']
})
export class DevicesGraphComponent {
  chartParameters: LineChartConfiguration;
  
  colorScheme = {
    domain: ['#1464A5', '#eeeeee', '#c5c5c5']
  };

  constructor() {
    this.chartParameters = new LineChartConfiguration();
    this.chartParameters.isArea = [true];
    this.chartParameters.interactive = false;
    this.chartParameters.useInteractiveGuideline = false;
  }

  createFilter(values: Array<{ attr, value }>): Expression {
    console.log("hola");
    let filters: Array<Expression> = [];
    values.forEach(fil => {
      if (fil.value) {
        if (fil.attr === 'rangoFechas') {
          filters.push(FilterExpressionUtils.buildComplexExpression(fil.value.startDate, fil.value.endDate, FilterExpressionUtils.OP_AND));
        }
      }
    });

    // Build complex expression
    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) => FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND));
    } else {
      return null;
    }
  }
}