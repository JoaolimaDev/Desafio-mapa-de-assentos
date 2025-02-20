import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-seat-selector',
  imports: [MatIconModule, CommonModule],
  templateUrl: './seat-selector.component.html',
  styleUrl: './seat-selector.component.css'
})
export class SeatSelectorComponent {


  seats: { id: number; selected: boolean }[] = Array.from({ length: 15 }, (_, i) => ({
    id: i,
    selected: false,
  }));

  toggleSeat(seat: any) {
    seat.selected = !seat.selected;
    
  }

}
