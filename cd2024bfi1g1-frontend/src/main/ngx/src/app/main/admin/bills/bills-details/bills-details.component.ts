import { Component, ViewChild } from '@angular/core';
import { OFormComponent, OIntegerInputComponent, ORealInputComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-bills-details',
  templateUrl: './bills-details.component.html',
  styleUrls: ['./bills-details.component.css']
})
export class BillsDetailsComponent {

  @ViewChild('bilForm', { static: true }) bilForm!: OFormComponent;
  @ViewChild('fixedPrice', { static: false }) fixedPrice!: ORealInputComponent;
  @ViewChild('devPrice', { static: false }) devPrice!: ORealInputComponent;
  @ViewChild('bundlePrice', { static: false }) bundlePrice!: ORealInputComponent;
  @ViewChild('bilDevices', { static: false }) devices!: OIntegerInputComponent;
  @ViewChild('bilMeasurements', { static: false }) measurements!: OIntegerInputComponent;
  @ViewChild('bundleRequests', { static: false }) bundleRequests!: OIntegerInputComponent;

  fixedPriceValue: number = 0;
  devPriceValue: number = 0;
  bundlePriceValue: number = 0;

  fixedQuantityValue: number = 1;
  devQuantityValue: number = 0;
  bundleQuantityValue: number = 0;
  measurementsValue: number = 0;
  bundleRequestsValue: number = 0;

  fixedSubtotalValue: number = 0;
  devSubtotalValue: number = 0;
  bundleSubtotalValue: number = 0;
  totalValue: number = 0;

  ngAfterViewInit() {
    this.bilForm.onDataLoaded.subscribe(() => this.fillTable());
  }

  fillTable() {
    this.fixedPriceValue = this.fixedPrice ? this.fixedPrice.getValue() : 0;
    this.devPriceValue = this.devPrice ? this.devPrice.getValue() : 0;
    this.bundlePriceValue = this.bundlePrice ? this.bundlePrice.getValue() : 0;

    this.devQuantityValue = this.devices ? this.devices.getValue() : 0;
    this.measurementsValue = this.measurements ? this.measurements.getValue() : 0;
    this.bundleRequestsValue = this.bundleRequests ? this.bundleRequests.getValue() : 0;
    this.bundleQuantityValue = Math.ceil(this.measurementsValue / this.bundleRequestsValue);

    this.fixedSubtotalValue = this.fixedPriceValue * this.fixedQuantityValue;
    this.devSubtotalValue = this.devPriceValue * this.devQuantityValue;
    this.bundleSubtotalValue = parseFloat(Number(this.bundlePriceValue * this.bundleQuantityValue).toFixed(2));

    this.totalValue = parseFloat(Number(this.fixedSubtotalValue + this.devSubtotalValue + this.bundleSubtotalValue).toFixed(2));
  }

}
