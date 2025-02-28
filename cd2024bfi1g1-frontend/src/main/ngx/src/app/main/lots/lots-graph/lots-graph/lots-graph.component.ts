import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import cytoscape from 'cytoscape';
import dagre from 'cytoscape-dagre';

cytoscape.use(dagre);

@Component({
  selector: 'app-lots-graph',
  templateUrl: './lots-graph.component.html',
  styleUrls: ['./lots-graph.component.css']
})
export class LotsGraphComponent implements AfterViewInit {

  @ViewChild('cy') graphContainer!: ElementRef;

  ngAfterViewInit() {
    cytoscape({
      container: this.graphContainer.nativeElement,

      elements: [
        { data: { id: 'C1', label: 'C1' } },
        { data: { id: 'C2', label: 'C2' } },
        { data: { id: 'C3', label: 'C3' } },
        { data: { id: 'C4', label: 'C4' } },

        { data: { id: 'C1_C2', source: 'C1', target: 'C2' } },
        { data: { id: 'C1_C3', source: 'C1', target: 'C3' } },
        { data: { id: 'C2_C4', source: 'C2', target: 'C4' } },
        { data: { id: 'C3_C4', source: 'C3', target: 'C4' } }
      ],

      style: [
        { selector: 'node', style: { 'background-color': '#007bff', 'label': 'data(label)', 'color': 'black', 'text-valign': 'center', 'text-halign': 'center' } },
        { selector: 'edge', style: { 'width': 2, 'line-color': '#666', 'target-arrow-shape': 'triangle', 'curve-style': 'bezier' } }
      ],

      layout: {
        name: 'dagre',
        rankDir: 'LR', // 'LR' = Left to Right (Izquierda a Derecha)
        align: 'UL', // Alinea en la parte superior izquierda
        nodeSep: 50,  // Separación horizontal
        edgeSep: 10,  // Separación entre aristas
        rankSep: 100  // Separación vertical entre niveles
      } as any
    });
  }

}
