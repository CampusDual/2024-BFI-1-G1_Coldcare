import { Component } from "@angular/core";
import { Expression, FilterExpressionUtils} from 'ontimize-web-ngx';
import { LineChartConfiguration } from 'ontimize-web-ngx-charts';

@Component({
  selector: "app-medidas-home",
  templateUrl: "./medidas-home.component.html",
  styleUrls: ["./medidas-home.component.css"],
})

export class MedidasAdminComponent {
 
    dataArray: any = [];
    chartData = [];
  
    chartParameters: LineChartConfiguration;
    
    colorScheme = {
      domain: ['#1464A5', '#eeeeee', '#c5c5c5']
    };
  
    constructor() {
      this.chartParameters = new LineChartConfiguration();
      this.chartParameters.isArea = [true];
      this.chartParameters.interactive = false;
      this.chartParameters.useInteractiveGuideline = false;
      this.chartParameters.xAxis = "ME_DATE";
      this.chartParameters.yAxis = ["ME_TEMP"];
      this.chartParameters.xDataType = "timeDetail";
    }
  
    createFilter(values: Array<{ attr, value }>): Expression {
      let filters: Array<Expression> = [];
      values.forEach(fil => {
        if (fil.value) {
          if (fil.attr === 'DEV_ID') {
            filters.push(FilterExpressionUtils.buildExpressionEquals('DEV_ID', fil.value));
          }
          if (fil.attr === 'ME_DATE') {
            filters.push(FilterExpressionUtils.buildExpressionMoreEqual('ME_DATE', fil.value.startDate._d.getTime()));
          }
          if (fil.attr === 'ME_DATE') {
            filters.push(FilterExpressionUtils.buildExpressionLessEqual('ME_DATE', fil.value.endDate._d.getTime()));
          }
        }
      });
  
      if (filters.length > 0) {
        return filters.reduce((exp1, exp2) => FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND));
      } else {
        return null;
      }
    }
  

}
