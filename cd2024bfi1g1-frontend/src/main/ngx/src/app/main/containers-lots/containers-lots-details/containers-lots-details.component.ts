import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Expression, FilterExpressionUtils, OTableComponent, OTextInputComponent, OTranslateService } from 'ontimize-web-ngx';
import { LineChartConfiguration, OChartComponent } from 'ontimize-web-ngx-charts';
import { SecondsToTimePipe } from 'src/app/shared/components/pipes/seconds-to-time.pipe';

@Component({
  selector: 'app-containers-lots-details',
  templateUrl: './containers-lots-details.component.html',
  styleUrls: ['./containers-lots-details.component.css']
})
export class ContainersLotsDetailsComponent {

  @ViewChild('measurementsTemperatureTable', { static: false }) measurementsTemperatureTable: OTableComponent;
  @ViewChild('measurementsHumidityTable', { static: false }) measurementsHumidityTable: OTableComponent;
  @ViewChild('lineChartBasic', { static: false }) lineChart: OChartComponent;
  @ViewChild('totalTime', { static: false }) totalTime!: OTextInputComponent;
  @ViewChild('averageTime', { static: false }) averageTime!: OTextInputComponent;
  @ViewChild('maxTime', { static: false }) maxTime!: OTextInputComponent;
  @ViewChild('activeAlert', { static: false }) activeAlert!: OTextInputComponent;
  @ViewChild('lastHumidity', { static: false }) lastHumidity!: OTextInputComponent;
  @ViewChild('lastTemp', { static: false }) lastTemp!: OTextInputComponent;

  dataArrayTemp: any = [];
  dataArrayHum: any = [];
  chartData = [];
  activeAlertVisible: number = 0;
  totalTimeVisible: string = "N/A";
  averageTimeVisible: string = "N/A";
  maxTimeVisible: string = "N/A";
  lastHumidityVisible: string = "N/A";
  lastTempVisible: string = "N/A";
  containerLot: any = {};

  chartParametersTemp: LineChartConfiguration;
  chartParametersHum: LineChartConfiguration;

  tempField: string = "";
  humidityField: string = "";

  // Paleta de colores para dispositivos
  deviceColors = [
    '#1464A5', '#FF5733', '#FFC300', '#28A745', '#6F42C1',
    '#E74C3C', '#8E44AD', '#F39C12', '#C0392B', '#3498DB'
  ];

  colorSchemeTemp = {
    domain: ['#1464A5']
  };

  colorSchemeHum = {
    domain: ['#31a514']
  };

  // Mapa de colores asignados a dispositivos
  deviceColorMap: { [key: string]: string } = {};

  formatDate(d: number): string {
    const date = new Date(d);
    return date.toLocaleString('es-ES', { month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' }).replace(',', '');
  }

  constructor(
    private secondsToTime: SecondsToTimePipe,
    private translator: OTranslateService,
    private router: Router
  ) {

    // Configuración del gráfico de temperatura
    this.chartParametersTemp = new LineChartConfiguration();
    this.chartParametersTemp.isArea = [true];
    this.chartParametersTemp.interactive = true;
    this.chartParametersTemp.useInteractiveGuideline = false;
    this.chartParametersTemp.xAxis = "ME_DATE";
    this.chartParametersTemp.xDataType = this.formatDate;
    this.chartParametersTemp['showTooltip'] = true;

    // Configuración del gráfico de humedad
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

  // Carga datos gráficas
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

  fillChart(ev: any) {
    this.tempField = this.translator.get('TEMPERATURE');
    this.humidityField = this.translator.get('HUMIDITY');

    this.chartParametersTemp.yAxis = [this.tempField];
    this.chartParametersHum.yAxis = [this.humidityField];

    const devicesData = {};
    const uniqueDevices = new Set();

    ev.forEach(row => {
      const devId = row.DEV_NAME;
      uniqueDevices.add(row.DEV_NAME);

      if (!devicesData[devId]) {
        devicesData[devId] = [];
      }

      devicesData[devId].push({
        name: row.DEV_NAME,
        [this.tempField]: row.ME_TEMP,
        [this.humidityField]: row.ME_HUMIDITY,
        "ME_DATE": row.ME_DATE,
      });
    });

    let colorIndex = 0;
    uniqueDevices.forEach(devId => {
      const devIdStr = String(devId);
      if (!this.deviceColorMap[devIdStr]) {
        this.deviceColorMap[devIdStr] = this.deviceColors[colorIndex % this.deviceColors.length];
        colorIndex++;
      }
    });

    this.dataArrayTemp = Object.keys(devicesData).map(devId => ({
      name: devId,
      series: devicesData[devId].map(point => ({
        name: point.ME_DATE,
        value: point[this.tempField]
      }))
    }));

    this.dataArrayHum = Object.keys(devicesData).map(devId => ({
      name: devId,
      series: devicesData[devId].map(point => ({
        name: point.ME_DATE,
        value: point[this.humidityField]
      }))
    }));

    this.colorSchemeTemp = { domain: Object.values(this.deviceColorMap) };
    this.colorSchemeHum = { domain: Object.values(this.deviceColorMap) };
  }

  fillData(e: any) {
    this.containerLot = e;
    if (e.TOTAL_TIME !== undefined) {
      this.totalTimeVisible = this.secondsToTime.transform(this.totalTime.getValue());
    }
    if (e.AVERAGE_TIME !== undefined) {
      this.averageTimeVisible = this.secondsToTime.transform(this.averageTime.getValue());
    }
    if (e.MAX_TIME !== undefined) {
      this.maxTimeVisible = this.secondsToTime.transform(this.maxTime.getValue());
    }
    if (e.ACTIVE_ALERTS !== undefined) {
      this.activeAlertVisible = this.activeAlert.getValue();
    }
    if (e.ME_HUMIDITY !== undefined) {
      this.lastHumidityVisible = this.lastHumidity.getValue() + "%";
    }
    if (e.ME_TEMP !== undefined) {
      this.lastTempVisible = this.lastTemp.getValue() + " Cº";
    }
  }

  public translateLabel() {
    if (this.activeAlertVisible === 0) {
      return this.translator.get("NO_ALERTS_ACTIVE_TEXT");
    } else if (this.activeAlertVisible === 1) {
      return this.translator.get("ALERTS_ACTIVE_TEXT_SINGULAR").replace("#ALT_COUNT#", this.activeAlertVisible.toString());
    } else {
      return this.translator.get("ALERTS_ACTIVE_TEXT").replace("#ALT_COUNT#", this.activeAlertVisible.toString());
    }
  }
}
