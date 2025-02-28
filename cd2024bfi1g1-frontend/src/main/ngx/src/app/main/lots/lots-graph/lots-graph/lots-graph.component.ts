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
    // Ordenar los contenedores por fecha de inicio (CL_START_DATE)
    const sortedData = [...data].sort((a, b) => a.CL_START_DATE - b.CL_START_DATE);

    const nodes = new Map<string, any>();
    const edges: any[] = [];

    sortedData.forEach((entry, index) => {
      const containerId = entry.CNT_ID.toString();
      const containerName = entry.CNT_NAME;
      const hasAlert = entry.HAS_ALERT === true || entry.HAS_ALERT === "true" ? "true" : "false";

      // Agregar nodo si no existe
      if (!nodes.has(containerId)) {
        nodes.set(containerId, { data: { id: containerId, label: containerName, hasAlert: hasAlert } });
      }

      // Conectar este contenedor con los anteriores si tienen una fecha de inicio anterior
      for (let i = 0; i < index; i++) {
        const prevContainer = sortedData[i];

        if (prevContainer.CL_END_DATE <= entry.CL_START_DATE) {
          edges.push({
            data: {
              id: `${prevContainer.CNT_ID}_${containerId}`,
              source: prevContainer.CNT_ID.toString(),
              target: containerId
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
            'background-color': 'red' // Color rojo para nodos con HAS_ALERT true
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
