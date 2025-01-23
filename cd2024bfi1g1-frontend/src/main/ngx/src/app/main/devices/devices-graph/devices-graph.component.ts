import { Component, ViewChild } from '@angular/core';
import { Expression, FilterExpressionUtils, OTableComponent } from 'ontimize-web-ngx';
import { DataAdapterUtils, LineChartConfiguration, OChartComponent } from 'ontimize-web-ngx-charts';

@Component({
  selector: 'app-devices-graph',
  templateUrl: './devices-graph.component.html',
  styleUrls: ['./devices-graph.component.css']
})
export class DevicesGraphComponent {

  //Grafica temperatura
  @ViewChild('measurementsTemperatureTable', { static: false }) measurementsTemperatureTable: OTableComponent;
  //Grafica humedad
  @ViewChild('measurementsHumidityTable', { static: false }) measurementsHumidityTable: OTableComponent;

  @ViewChild('lineChartBasic', { static: false }) lineChart: OChartComponent;

  dataArrayTemp: any = [];
  dataArrayHum: any = [];
  chartData = [];

  chartParametersTemp: LineChartConfiguration;
  chartParametersHum: LineChartConfiguration;

  colorScheme = {
    domain: ['#1464A5', '#eeeeee', '#c5c5c5']
  };

  constructor() {
    //Temperatura
    this.chartParametersTemp = new LineChartConfiguration();
    this.chartParametersTemp.isArea = [true];
    this.chartParametersTemp.interactive = true;
    this.chartParametersTemp.useInteractiveGuideline = false;
    this.chartParametersTemp.xAxis = "ME_DATE";
    this.chartParametersTemp.yAxis = ["ME_TEMP"];
    this.chartParametersTemp.xDataType = (d: number): string => {
      const date = new Date(d);
      const day = String(date.getDate()).padStart(2, '0');
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');

      return `${day}/${month} ${hours}:${minutes}`;
    };
    this.chartParametersTemp['showTooltip'] = true;

    //Humedad
    this.chartParametersHum = new LineChartConfiguration();
    this.chartParametersHum.isArea = [true];
    this.chartParametersHum.interactive = false;
    this.chartParametersHum.useInteractiveGuideline = false;
    this.chartParametersHum.xAxis = "ME_DATE";
    this.chartParametersHum.yAxis = ["ME_HUMIDITY"];
    this.chartParametersHum.xDataType = "timeDetail";
  }

  createFilter(values: Array<{ attr, value }>): Expression {

    console.log('Valores del filtro:', values);

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

    console.log('Filtros generados:', filters);

    if (filters.length > 0) {
      return filters.reduce((exp1, exp2) => FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND));
    } else {
      return null;
    }
  }

  //LLenar tablas
  fillChart(ev: any) {
    console.log({ ev });

    this.dataArrayTemp = DataAdapterUtils.createDataAdapter(
      this.chartParametersTemp
    ).adaptResult(ev);

    this.dataArrayHum = DataAdapterUtils.createDataAdapter(
      this.chartParametersHum
    ).adaptResult(ev);
  }

}
