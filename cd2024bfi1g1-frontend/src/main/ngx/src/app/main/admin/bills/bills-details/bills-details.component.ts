import { Component,  } from '@angular/core';

@Component({
  selector: 'app-bills-details',
  templateUrl: './bills-details.component.html',
  styleUrls: ['./bills-details.component.css']
})
export class BillsDetailsComponent {

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

  fillTable(data:any) {
    console.log({data});
    this.fixedPriceValue = data.PP_FIXED_PRICE ? data.PP_FIXED_PRICE : 0;
    this.devPriceValue = data.PP_DEV_PRICE ? data.PP_DEV_PRICE : 0;
    this.bundlePriceValue = data.PP_BUNDLE_PRICE ? data.PP_BUNDLE_PRICE : 0;

    this.devQuantityValue = data.BIL_DEVICES ? data.BIL_DEVICES : 0;
    this.measurementsValue = data.BIL_MEASUREMENTS ? data.BIL_MEASUREMENTS : 0;
    this.bundleRequestsValue = data.PP_BUNDLE_REQUESTS ? data.PP_BUNDLE_REQUESTS : 0;
    this.bundleQuantityValue = Math.ceil(this.measurementsValue / this.bundleRequestsValue);

    this.fixedSubtotalValue = this.fixedPriceValue * this.fixedQuantityValue;
    this.devSubtotalValue = this.devPriceValue * this.devQuantityValue;
    this.bundleSubtotalValue = parseFloat(Number(this.bundlePriceValue * this.bundleQuantityValue).toFixed(2));

    this.totalValue = parseFloat(Number(this.fixedSubtotalValue + this.devSubtotalValue + this.bundleSubtotalValue).toFixed(2));
  }

}
