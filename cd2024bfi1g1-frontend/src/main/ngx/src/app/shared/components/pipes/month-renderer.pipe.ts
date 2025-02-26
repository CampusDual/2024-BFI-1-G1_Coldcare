import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'monthRenderer'
})
export class MonthRendererPipe implements PipeTransform {

  transform(value: number): string {
    const months = [
      'JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUNE',
      'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER'
    ];
    return value >= 1 && value <= 12 ? months[value - 1] : '';
  }

}
