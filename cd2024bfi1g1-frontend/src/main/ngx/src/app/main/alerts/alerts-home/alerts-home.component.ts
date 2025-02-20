import { Component, ViewChild, ChangeDetectorRef } from '@angular/core';
import { OntimizeService, Expression, FilterExpressionUtils, OTableComponent, Util } from 'ontimize-web-ngx';


@Component({
  selector: 'app-alerts-home',
  templateUrl: './alerts-home.component.html',
  styleUrls: ['./alerts-home.component.css']
})
export class AlertsHomeComponent {
  @ViewChild("alertsTable") alertsTable: OTableComponent;
  constructor(private ontimizeService: OntimizeService) {
    this.ontimizeService.configureService(
      this.ontimizeService.getDefaultServiceConfiguration('alertsWithCalculatedColumns')
    );
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration('alerts'));

  }

  selectionRow(event) {

    const selectedRows = this.alertsTable.getSelectedItems();

    if (selectedRows.length === 0) {
      console.log("No hay filas seleccionadas");
      return;
    }

    const dataToSave = selectedRows.map(row => ({

      ALT_STATE: row.ALT_STATE = row.ALT_STATE === true ? false : true,
      ALT_ID: row.ALT_ID
    }));

    dataToSave.map(row => {

      const attrMap = { ALT_STATE: row.ALT_STATE };
      const keyMap = { ALT_ID: row.ALT_ID }

      this.ontimizeService.update(keyMap, attrMap, 'alertsWithCalculatedColumns').subscribe({
        next(value) {

        },
        error(err) {
          console.log(err)
        },
        complete() {

        }
      });

    })

  }

  createFilter(values: Array<{ attr; value }>): Expression {

    let filters: Array<Expression> = [];

    values.forEach(fil => {

      if (fil.attr === "estado" && Util.isDefined(fil.value)) {

        let estado = String(fil.value)
        if (estado == "0") {
          fil.value = false
        }

        if (estado == "1") {
          fil.value = true
        }

        if (estado == "2") {
          fil.value = "true or false"
        }

        if (!fil.value === true && !(fil.value == " ")) {
          fil.value = false;
        }
      }

      if (Util.isDefined(fil.value)) {
        const attributeMapping = {
          lote: "lot_name",
          contenedor: "cnt_name",
          estado: "alt_state"
        };

        const fieldName = attributeMapping[fil.attr];

        if (fieldName) {

          if (fil.value === "true or false") {
            const filterTrue = FilterExpressionUtils.buildExpressionLike(fieldName, '%true%');
            const filterFalse = FilterExpressionUtils.buildExpressionLike(fieldName, '%false%');
            filters.push(FilterExpressionUtils.buildComplexExpression(filterTrue, filterFalse, FilterExpressionUtils.OP_OR));
          } else {
            const valueAsString = String(fil.value);
            filters.push(FilterExpressionUtils.buildExpressionLike(fieldName, valueAsString));
          }
        }
      }
    });


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
}