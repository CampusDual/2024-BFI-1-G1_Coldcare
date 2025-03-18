import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatState'
})
export class FormatStatePipe implements PipeTransform {

  transform(value: number): string {
    if (value === null || value === undefined) return '';

    const estados: { [key: number]: string } = {
      1: 'PENDING',
      2: 'INITIATED',
      3: 'FINISHED',
    };

    return estados[value] || 'Desconocido';
  }

}
