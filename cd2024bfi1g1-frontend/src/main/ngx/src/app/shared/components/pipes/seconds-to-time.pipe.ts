import { Injectable, Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'secondsToTime'
})
@Injectable({ providedIn: 'root' })
export class SecondsToTimePipe implements PipeTransform {

  transform(value: number): string {

    if (!value || value < 0) return '';

    const hours = Math.floor(value / 3600);
    const minutes = Math.floor((value % 3600) / 60);
    const secs = Math.floor(value % 60);

    return `${hours.toString().padStart(2, '0')} h ${minutes.toString().padStart(2, '0')} min ${secs.toString().padStart(2, '0')} s`;
  }
}
