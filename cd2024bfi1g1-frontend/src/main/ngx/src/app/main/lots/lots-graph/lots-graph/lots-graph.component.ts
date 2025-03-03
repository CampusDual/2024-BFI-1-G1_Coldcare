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
    const graph = new Map<string, string[]>(); // Para rastrear las conexiones existentes

    sortedData.forEach((entry, index) => {
      const containerId = entry.CNT_ID.toString();
      const containerName = entry.CNT_NAME;
      const hasAlert = entry.HAS_ALERT === true || entry.HAS_ALERT === "true" ? "true" : "false";

      // Agregar nodo si no existe
      if (!nodes.has(containerId)) {
        nodes.set(containerId, { data: { id: containerId, label: containerName, hasAlert: hasAlert } });
        graph.set(containerId, []); // Inicializar la lista de conexiones para este nodo
      }

      // Conectar este contenedor con los contenedores anteriores que cumplan la condición de fecha
      for (let i = index - 1; i >= 0; i--) {
        const prevContainer = sortedData[i];
        const prevContainerId = prevContainer.CNT_ID.toString();

        if (prevContainer.CL_END_DATE <= entry.CL_START_DATE) {
          // Verificar si ya existe una ruta indirecta entre prevContainerId y containerId
          if (!this.hasIndirectPath(graph, prevContainerId, containerId)) {
            edges.push({
              data: {
                id: `${prevContainerId}_${containerId}`,
                source: prevContainerId,
                target: containerId
              }
            });
            graph.get(prevContainerId)?.push(containerId); // Registrar la conexión en el grafo
          }
          // break; // Solo conectamos con el contenedor anterior más reciente que cumpla la condición
        }
      }
    });

    return [...nodes.values(), ...edges];
  }

  // Función para verificar si ya existe una ruta indirecta entre dos nodos
  hasIndirectPath(graph: Map<string, string[]>, source: string, target: string): boolean {
    const visited = new Set<string>();
    const queue: string[] = [source];

    while (queue.length > 0) {
      const current = queue.shift()!;
      if (current === target) {
        return true; // Existe una ruta indirecta
      }
      if (!visited.has(current)) {
        visited.add(current);
        const neighbors = graph.get(current) || [];
        queue.push(...neighbors);
      }
    }

    return false; // No existe una ruta indirecta
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
