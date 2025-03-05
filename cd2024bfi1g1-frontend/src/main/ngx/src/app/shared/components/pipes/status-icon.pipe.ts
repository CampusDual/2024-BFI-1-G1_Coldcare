import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusIcon'
})
export class StatusIconPipe implements PipeTransform {

  transform(value: string): { icon: string, class: string } {
    if (!value) return { icon: 'help_outline', class: 'default' };

    switch (value.toUpperCase()) {
      case 'PENDING':
        return { icon: 'hourglass_empty', class: 'pending' };
      case 'INITIATED':
        return { icon: 'local_shipping', class: 'initiated' };
      case 'FINISHED':
        return { icon: 'check_circle', class: 'finished' };
      default:
        return { icon: 'help_outline', class: 'default' };
    }
  }
}
