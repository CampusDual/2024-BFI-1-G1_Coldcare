import { identifierName } from '@angular/compiler';
import { Component, ViewChild } from '@angular/core';
import { Expression, FilterExpressionUtils, OTableComponent, Util } from 'ontimize-web-ngx';
import { identity } from 'rxjs';

@Component({
  selector: 'app-alerts-home',
  templateUrl: './alerts-home.component.html',
  styleUrls: ['./alerts-home.component.css']
})
export class AlertsHomeComponent {
  @ViewChild("alertsTable") alertsTable: OTableComponent;

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

  editionStarted(arg: any) {
    console.log('editionStarted');
    console.log(arg);
  }

  editionCancelled(arg: any) {
    console.log('editionCancelled');
    console.log(arg);
  }

  editionCommitted(arg: any) {
    console.log('editionCommitted');
    console.log(arg);
  }

  selectionRow(event) {

    console.log(this.alertsTable.getSelectedItems());
  }
  /*
    selectionRow() {
  
    /*  const data = this.alertsTable.getDataArray();
      for (let row of data) {
        if (row.getValue("ALT_STATE")) {
          console.log(row.getValue("ALT_STATE"));
        }
        console.log(row);
      }
  
      // Verificar si Ontimize proporciona un método para obtener las filas seleccionadas
      const selectedRows = this.alertsTable.selectAll;
  
      console.log("a", this.alertsTable.selectAll);
      console.log("b", this.alertsTable.selectAllCheckbox);
      console.log("c", this.alertsTable.selectedRow);
      console.log(selectedRows);
      // Si 'selectedRows' es una propiedad de filas seleccionadas, utilizamos directamente
      if (Array.isArray(selectedRows) && selectedRows.length > 0) {
        console.log('Filas seleccionadas:', selectedRows);
        selectedRows.forEach(row => {
          console.log('Guardando estado para fila:', row);
          // Aquí puedes realizar cualquier acción, como guardar el estado de la fila
        });
      } else {
        console.log('No hay filas seleccionadas');
      }
    }
  */

}
