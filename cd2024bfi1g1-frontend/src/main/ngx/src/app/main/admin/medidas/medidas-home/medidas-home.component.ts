import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-medidas-home",
  templateUrl: "./medidas-home.component.html",
  styleUrls: ["./medidas-home.component.css"],
})
export class MedidasAdminComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
    // Aquí iría la lógica de inicialización
    console.log("Componente MedidasHome ha sido inicializado.");
  }

  }
