import { Component, ViewChild } from '@angular/core';
import { Expression, FilterExpressionUtils, OTableComponent } from 'ontimize-web-ngx';
import { DataAdapterUtils, LineChartConfiguration, OChartComponent } from 'ontimize-web-ngx-charts';

@Component({
  selector: 'app-devices-graph',
  templateUrl: './devices-graph.component.html',
  styleUrls: ['./devices-graph.component.css']
})
export class DevicesGraphComponent {
  @ViewChild('measurementsTable', { static: false }) measurementsTable: OTableComponent;
  @ViewChild('lineChartBasic', { static: false }) lineChart: OChartComponent;

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
    console.log("hola");
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

  fillChart() {
    this.dataArray = DataAdapterUtils.createDataAdapter(
      this.chartParameters
    ).adaptResult(this.measurementsTable.getDataArray());

    console.log('Datos de la tabla:', this.dataArray, this.measurementsTable.getDataArray());
    // this.lineChart.data = this.dataArray;
    // this.updateChartData(this.dataArray);
  }

  updateChartData(dataArray: any[]) {
    if (this.lineChart) {
      this.chartData = dataArray
        .map(row => ({
          ME_DATE: row['ME_DATE'],
          ME_TEMP: row['ME_TEMP']
        }));

      console.log('Datos para la gráfica:', this.chartData);

      this.lineChart.data = this.chartData;
    }
  }
}