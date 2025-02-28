import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import cytoscape from 'cytoscape';

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
        { data: { id: 'A', label: 'Nodo A' } },
        { data: { id: 'B', label: 'Nodo B' } },
        { data: { id: 'C', label: 'Nodo C' } },
        { data: { id: 'D', label: 'Nodo D' } },
        { data: { id: 'E', label: 'Nodo E' } },

        { data: { id: 'AB', source: 'A', target: 'B' } },
        { data: { id: 'AC', source: 'A', target: 'C' } },
        { data: { id: 'AD', source: 'A', target: 'D' } },
        { data: { id: 'AE', source: 'A', target: 'E' } },
        { data: { id: 'BC', source: 'B', target: 'C' } }
      ],

      style: [
        { selector: 'node', style: { 'background-color': '#007bff', 'label': 'data(label)', 'color': 'black', 'text-valign': 'center', 'text-halign': 'center' } },
        { selector: 'edge', style: { 'width': 2, 'line-color': '#666', 'target-arrow-shape': 'triangle', 'curve-style': 'bezier' } }
      ],

      layout: {
        name: 'breadthfirst',
        fit: true
      }
    });
  }

}
