import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'secondsToTime'
})
export class SecondsToTimePipe implements PipeTransform {

  transform(value: number): string {

    if (!value || value < 0) return '';

    const days = Math.floor(value / 86400);
    const hours = Math.floor((value % 86400) / 3600);
    const minutes = Math.floor((value % 3600) / 60);
    const secs = Math.floor(value % 60);

    return `${days} d, ${hours.toString().padStart(2, '0')} h ${minutes.toString().padStart(2, '0')} min ${secs.toString().padStart(2, '0')} seg`;
  }
}
