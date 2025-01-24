import { Component, ViewChild } from '@angular/core';
import { Expression, FilterExpressionUtils, OTableComponent } from 'ontimize-web-ngx';
import { DataAdapterUtils, LineChartConfiguration, OChartComponent } from 'ontimize-web-ngx-charts';

@Component({
  selector: 'app-devices-detail',
  templateUrl: './devices-detail.component.html',
  styleUrls: ['./devices-detail.component.css']
})
export class DevicesDetailComponent {

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

  formatDate(d: number): string {
    const date = new Date(d);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    //d.toLocaleString('es-ES',{'month':'numeric',day:'numeric',hour:'2-digit',minute:'2-digit'}).replace(',','')

    return `${day}/${month} ${hours}:${minutes}`;
  }

  constructor() {
    //Temperatura
    this.chartParametersTemp = new LineChartConfiguration();
    this.chartParametersTemp.isArea = [true];
    this.chartParametersTemp.interactive = true;
    this.chartParametersTemp.useInteractiveGuideline = false;
    this.chartParametersTemp.xAxis = "ME_DATE";
    this.chartParametersTemp.yAxis = ["ME_TEMP"];
    this.chartParametersTemp.xDataType = this.formatDate;
    this.chartParametersTemp['showTooltip'] = true;
    //this.chartParametersTemp.tooltip['valueType'] = 'time';
    //this.chartParametersTemp['valueType'] = (d: number): string => {

    //  return new Date(d).toString();
    //};

    //Truquito Alvaro
    //const chart = (<any>this.lineChart).formatTime;

    console.log('Log:',this.chartParametersTemp['valueType']);

    //Humedad
    this.chartParametersHum = new LineChartConfiguration();
    this.chartParametersHum.isArea = [true];
    this.chartParametersHum.interactive = false;
    this.chartParametersHum.useInteractiveGuideline = false;
    this.chartParametersHum.xAxis = "ME_DATE";
    this.chartParametersHum.yAxis = ["ME_HUMIDITY"];
    this.chartParametersHum.xDataType = (d: number): string => {
      const date = new Date(d);
      const day = String(date.getDate()).padStart(2, '0');
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');

      return `${day}/${month} ${hours}:${minutes}`;
    };
    this.chartParametersHum['showTooltip'] = true;

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
