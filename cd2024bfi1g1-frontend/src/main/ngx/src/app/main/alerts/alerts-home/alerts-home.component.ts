import { Component, ViewChild } from '@angular/core';
import { OntimizeService, Expression, FilterExpressionUtils, OTableComponent, Util } from 'ontimize-web-ngx';


@Component({
  selector: 'app-alerts-home',
  templateUrl: './alerts-home.component.html',
  styleUrls: ['./alerts-home.component.css']
})
export class AlertsHomeComponent {
  @ViewChild("alertsTable") alertsTable: OTableComponent;
  constructor(private ontimizeService: OntimizeService) { }

  createFilter(values: Array<{ attr; value }>): Expression {
    // Prepare simple expressions from the filter components values
    let filters: Array<Expression> = [];
    values.forEach(fil => {
      if (Util.isDefined(fil.value)) {
        const attributeMapping = {
          lote: "lot_name",
          contenedor: "cnt_name"
        };

        const fieldName = attributeMapping[fil.attr];
        if (fieldName) {
          filters.push(FilterExpressionUtils.buildExpressionLike(fieldName, fil.value));
        }

      }
    });

    // Build complex expression
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


  selectionRow(event) {

    const selectedRows = this.alertsTable.getSelectedItems();

    if (selectedRows.length === 0) {
      console.log("No hay filas seleccionadas");
      return;
    }


    const dataToSave = selectedRows.map(row => ({
      ALT_STATE: row.ALT_STATE = row.ALT_STATE === true ? false : true,
      LOT_NAME: row.LOT_NAME,
      AVG_HUMIDITY: row.AVG_HUMIDITY,
      MIN_TEMP_DEV: row.MIN_TEMP_DEV,
      ALT_DATE_INIT: row.ALT_DATE_INIT,
    }));


    this.ontimizeService.configureService(
      this.ontimizeService.getDefaultServiceConfiguration('alertsWithCalculatedColumns')
    );

    this.ontimizeService.query(dataToSave, [], 'alertsWithCalculatedColumns', { operation: 'update' })
      .subscribe(
        response => {
          console.log('Datos actualizados correctamente:', response);
        },
        error => {
          console.error('Error al actualizar los datos:', error);
        }
      );
  }

}
