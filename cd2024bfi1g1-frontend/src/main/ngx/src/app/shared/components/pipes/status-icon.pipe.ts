import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusIcon'
})
export class StatusIconPipe implements PipeTransform {

  transform(value: string): string {
    if (!value) return '';

    switch (value.toUpperCase()) {
      case 'PENDING':
        return 'hourglass_empty'; // Ícono de espera
      case 'INITIATED':
        return 'local_shipping'; // Ícono de transporte
      case 'FINISHED':
        return 'check_circle'; // Ícono de finalizado
      default:
        return 'help_outline'; // Ícono por defecto (?)
    }
  }
}
