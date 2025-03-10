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
    const nodes = new Map<string, any>();
    const clIdToNodeId = new Map<number, string>(); // Mapa para buscar nodos por CL_ID
    const clIdToStartDate = new Map<number, number>(); // Para almacenar CL_START_DATE por CL_ID
    const edges: any[] = [];
    const destinations = new Set<number>(); // Conjunto de nodos destino

    // Primera pasada: Guardar CL_ID → CL_START_DATE y marcar nodos destino
    data.forEach(entry => {
      clIdToStartDate.set(entry.CL_ID, entry.CL_START_DATE);
      if (entry.CL_ID_DESTINY) {
        destinations.add(entry.CL_ID_DESTINY);
      }
    });

    // Segunda pasada: Detectar nodos iniciales
    data.forEach(entry => {
      const nodeId = entry.CNT_ID.toString();
      const nodeName = entry.CNT_NAME;
      const hasAlert = entry.HAS_ALERT ? "true" : "false";

      let isFirstNode = "false";

      if (!destinations.has(entry.CL_ID)) {
        isFirstNode = "true";
      } else if (entry.CL_ID_ORIGIN && clIdToStartDate.has(entry.CL_ID_ORIGIN)) {
        const originStartDate = clIdToStartDate.get(entry.CL_ID_ORIGIN)!;
        if (originStartDate > entry.CL_START_DATE) {
          isFirstNode = "true";
        }
      }

      if (!nodes.has(nodeId)) {
        nodes.set(nodeId, { data: { id: nodeId, label: nodeName, hasAlert, isFirstNode } });
      }

      // Guardar la relación CL_ID → CNT_ID
      clIdToNodeId.set(entry.CL_ID, nodeId);
    });

    // Tercera pasada: Crear aristas basadas en CL_ID_ORIGIN
    data.forEach(entry => {
      if (entry.CL_ID_ORIGIN && clIdToNodeId.has(entry.CL_ID_ORIGIN)) {
        const sourceId = clIdToNodeId.get(entry.CL_ID_ORIGIN)!;
        const targetId = clIdToNodeId.get(entry.CL_ID)!;

        if (sourceId !== targetId) {
          edges.push({ data: { id: `${sourceId}_${targetId}`, source: sourceId, target: targetId } });
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
        {
          selector: 'node',
          style: {
            'background-color': '#009BF5',
            'label': 'data(label)',
            'color': 'black',
            'text-valign': 'center',
            'text-halign': 'center'
          }
        },
        {
          selector: 'edge',
          style: {
            'width': 2,
            'line-color': '#999999',
            'target-arrow-shape': 'triangle',
            'curve-style': 'bezier'
          }
        },
        {
          selector: 'node[hasAlert = "true"][isFirstNode = "true"]',
          style: {
            'background-color': '#FF6161',
            'shape': 'rectangle'
          }
        },
        {
          selector: 'node[isFirstNode = "true"]:not([hasAlert = "true"])',
          style: {
            'background-color': '#009BF5',
            'shape': 'rectangle'
          }
        },
        {
          selector: 'node[hasAlert = "true"]:not([isFirstNode = "true"])',
          style: {
            'background-color': '#FF6161'
          }
        }
      ],
      layout: {
        name: 'dagre',
        ...({ rankDir: 'LR', nodeSep: 50, edgeSep: 10, rankSep: 100 } as any)
      },
      userZoomingEnabled: false,
      maxZoom: 90

    });
  }


}
