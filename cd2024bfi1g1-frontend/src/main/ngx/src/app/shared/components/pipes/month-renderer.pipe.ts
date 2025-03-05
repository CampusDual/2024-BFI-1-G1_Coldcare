import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'monthRenderer'
})
export class MonthRendererPipe implements PipeTransform {

  months = [
    'JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUNE',
    'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER'
  ];

  transform(value: number): string {

    return value >= 1 && value <= 12 ? this.months[value - 1] : '';
  }

}
