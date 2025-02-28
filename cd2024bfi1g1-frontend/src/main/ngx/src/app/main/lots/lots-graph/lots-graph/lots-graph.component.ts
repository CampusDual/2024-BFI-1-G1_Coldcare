import { Component, ElementRef, Input, SimpleChanges, ViewChild } from '@angular/core';
import cytoscape from 'cytoscape';
import dagre from 'cytoscape-dagre';

cytoscape.use(dagre);

@Component({
  selector: 'app-lots-graph',
  templateUrl: './lots-graph.component.html',
  styleUrls: ['./lots-graph.component.css']
})
export class LotsGraphComponent {

  @ViewChild('cy', { static: true }) graphContainer!: ElementRef;
  @Input() graphData: any[] = [];

  ngOnChanges(changes: SimpleChanges) {
    if (changes['graphData'] && this.graphData.length > 0) {
      this.renderGraph(this.transformDataToGraph(this.graphData));
    }
  }

  transformDataToGraph(data: any[]) {
    const sortedData = [...data].sort((a, b) => a.CL_START_DATE - b.CL_START_DATE);

    const nodes = new Map<string, any>();
    const edges: any[] = [];

    // Mapa para rastrear el último nodo creado para cada contenedor
    const lastNodeForContainer = new Map<string, string>();

    sortedData.forEach((entry, index) => {
      const containerId = entry.CNT_ID.toString();
      const containerName = entry.CNT_NAME;
      const hasAlert = entry.HAS_ALERT === true || entry.HAS_ALERT === "true" ? "true" : "false";

      // Crear un identificador único para cada aparición del contenedor
      const uniqueNodeId = `${containerId}_${index}`;

      // Agregar nodo si no existe
      if (!nodes.has(uniqueNodeId)) {
        nodes.set(uniqueNodeId, { data: { id: uniqueNodeId, label: containerName, hasAlert: hasAlert } });
      }

      // Conectar este contenedor con el último nodo del mismo contenedor (si existe)
      if (lastNodeForContainer.has(containerId)) {
        const lastNodeId = lastNodeForContainer.get(containerId)!;

        // Solo conectar si es un trasvase dentro del mismo contenedor
        if (entry.CNT_ID === sortedData[index - 1].CNT_ID) {
          edges.push({
            data: {
              id: `${lastNodeId}_${uniqueNodeId}`,
              source: lastNodeId,
              target: uniqueNodeId
            }
          });
        }
      }

      // Actualizar el último nodo creado para este contenedor
      lastNodeForContainer.set(containerId, uniqueNodeId);

      // Conectar este contenedor con los anteriores si tienen una fecha de inicio anterior
      for (let i = 0; i < index; i++) {
        const prevContainer = sortedData[i];
        const prevContainerId = prevContainer.CNT_ID.toString();

        // Solo conectar si no es el mismo contenedor y la fecha de fin es menor o igual a la fecha de inicio
        if (prevContainerId !== containerId && prevContainer.CL_END_DATE <= entry.CL_START_DATE) {
          const prevLastNodeId = lastNodeForContainer.get(prevContainerId)!;
          edges.push({
            data: {
              id: `${prevLastNodeId}_${uniqueNodeId}`,
              source: prevLastNodeId,
              target: uniqueNodeId
            }
          });
        }
      }
    });

    return [...nodes.values(), ...edges];
  }

  renderGraph(elements: any[]) {
    cytoscape({
      container: this.graphContainer.nativeElement,
      elements,
      style: [
        { selector: 'node', style: { 'background-color': '#007bff', 'label': 'data(label)', 'color': 'black', 'text-valign': 'center', 'text-halign': 'center' } },
        { selector: 'edge', style: { 'width': 2, 'line-color': '#666', 'target-arrow-shape': 'triangle', 'curve-style': 'bezier' } },
        {
          selector: 'node[hasAlert = "true"]',
          style: {
            'background-color': 'red'
          }
        },
      ],
      layout: {
        name: 'dagre',
        ...({ rankDir: 'LR', nodeSep: 50, edgeSep: 10, rankSep: 100 } as any)
      }
    });
  }

}
