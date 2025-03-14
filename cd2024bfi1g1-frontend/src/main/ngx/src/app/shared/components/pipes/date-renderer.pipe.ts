import { Injectable, Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({
  name: 'dateRenderer'
})
@Injectable({ providedIn: 'root' })
export class DateRendererPipe implements PipeTransform {
  constructor(private datePipe: DatePipe) { }

  transform(value: number, format: string = 'dd/MM/yyyy HH:mm:ss'): string | null {

    if (isNaN(value)) {
      return null;
    }

    return this.datePipe.transform(value, format);
  }

} 
