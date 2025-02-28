import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Expression, FilterExpressionUtils, OTableComponent, OTranslateService } from 'ontimize-web-ngx';
import { DataAdapterUtils, LineChartConfiguration, OChartComponent } from 'ontimize-web-ngx-charts';

@Component({
  selector: 'app-containers-lots-details',
  templateUrl: './containers-lots-details.component.html',
  styleUrls: ['./containers-lots-details.component.css']
})
export class ContainersLotsDetailsComponent {

  @ViewChild('measurementsTemperatureTable', { static: false }) measurementsTemperatureTable: OTableComponent;
  @ViewChild('measurementsHumidityTable', { static: false }) measurementsHumidityTable: OTableComponent;
  @ViewChild('lineChartBasic', { static: false }) lineChart: OChartComponent;

  dataArrayTemp: any = [];
  dataArrayHum: any = [];
  chartData = [];

  chartParametersTemp: LineChartConfiguration;
  chartParametersHum: LineChartConfiguration;

  colorSchemeTemp = {
    domain: ['#1464A5']
  };

  colorSchemeHum = {
    domain: ['#31a514']
  };

  tempField: string = "";
  humidityField: string = "";

  formatDate(d: number): string {
    const date = new Date(d);
    return date.toLocaleString('es-ES', { month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' }).replace(',', '');
  }

  constructor(
      private translator: OTranslateService,
      private router: Router
    ) {
      //Temperatura
      this.chartParametersTemp = new LineChartConfiguration();
      this.chartParametersTemp.isArea = [true];
      this.chartParametersTemp.interactive = true;
      this.chartParametersTemp.useInteractiveGuideline = false;
      this.chartParametersTemp.xAxis = "ME_DATE";
      this.chartParametersTemp.xDataType = this.formatDate;
      this.chartParametersTemp['showTooltip'] = true;
  
      //Humedad
      this.chartParametersHum = new LineChartConfiguration();
      this.chartParametersHum.isArea = [true];
      this.chartParametersHum.interactive = false;
      this.chartParametersHum.useInteractiveGuideline = false;
      this.chartParametersHum.xAxis = "ME_DATE";
      this.chartParametersHum.xDataType = this.formatDate;
      this.chartParametersHum['showTooltip'] = true;
    }

  public rowClass = (rowData: any, rowIndex: number): string | string[] => {

    const temp = rowData.ME_TEMP;
    const minTemp = rowData.MIN_TEMP;
    const maxTemp = rowData.MAX_TEMP;

    if (temp < minTemp || temp > maxTemp) {
      return "error-row";
    }
    return '';
  }

  public openContainersLotsDetail(selected: any) {
    const row = selected.row;
    if (row && row.CL_ID) {
      this.router.navigate(['main', 'containers-lots', row.CL_ID], { queryParams: { isdetail: true } });
    }
  }

  //Carga datos graficas
   createFilter(values: Array<{ attr, value }>): Expression {
  
      let filters: Array<Expression> = [];
      values.forEach(fil => {
        if (fil.value) {
          if (fil.attr === 'DEV_ID') {
            filters.push(FilterExpressionUtils.buildExpressionEquals('DEV_ID', fil.value));
          }
          if (fil.attr === 'ME_DATE') {
            filters.push(FilterExpressionUtils.buildExpressionMoreEqual('ME_DATE', fil.value.startDate));
          }
          if (fil.attr === 'ME_DATE') {
            filters.push(FilterExpressionUtils.buildExpressionLessEqual('ME_DATE', fil.value.endDate));
          }
        }
      });
  
      if (filters.length > 0) {
        return filters.reduce((exp1, exp2) => FilterExpressionUtils.buildComplexExpression(exp1, exp2, FilterExpressionUtils.OP_AND));
      } else {
        return null;
      }
    }
  
    /*
    Para modificar como se muestra la fecha del tooltip cuando ponemos el raton encima de uno de los puntos, habria que 
    modificar la linea 101 donde se carga la fecha en la variable "data" para modificar el formato de fecha que se le pasa:
      "ME_DATE": (new Date(row.ME_DATE)).toISOString(),
    El problema es que rompe por completo la representacion de los datos, es decir, no se representan los saltos temporales.
    Por lo tanto de momento no se modifica hasta que el cliente decida lo contrario. Una vez arreglado eliminar este comentario 
    */
  
    //LLenar tablas
    fillChart(ev: any) {
      this.tempField = this.translator.get('TEMPERATURE');
      this.humidityField = this.translator.get('HUMIDITY');
      this.chartParametersTemp.yAxis = [this.tempField];
      this.chartParametersHum.yAxis = [this.humidityField];
      const data = ev.map((row) => {
        return {
          [this.tempField]: row.ME_TEMP,
          "ME_DATE": row.ME_DATE,
          [this.humidityField]: row.ME_HUMIDITY
        }
      });
  
      this.dataArrayTemp = DataAdapterUtils.createDataAdapter(
        this.chartParametersTemp
      ).adaptResult(data);
  
      this.dataArrayHum = DataAdapterUtils.createDataAdapter(
        this.chartParametersHum
      ).adaptResult(data);
    }

}
