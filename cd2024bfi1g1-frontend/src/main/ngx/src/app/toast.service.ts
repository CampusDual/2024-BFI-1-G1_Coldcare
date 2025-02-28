import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor(private snackBar: MatSnackBar) { }

  showToast(message: string) {
    this.snackBar.open(message, 'Cerrar', {
      duration: 2000,
    });
  }
}
