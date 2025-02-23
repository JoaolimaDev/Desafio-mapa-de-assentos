import { SeatsPageable } from './../models/seats-pageable';
import { ApiService } from './../services/api.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Seat } from '../models/seat-response';

@Component({
  selector: 'app-seat-selector',
  imports: [MatIconModule, CommonModule],
  templateUrl: './seat-selector.component.html',
  styleUrl: './seat-selector.component.css'
})
export class SeatSelectorComponent {

  constructor(private apiService: ApiService) {}

  seats: Array<{ seatData: Seat; selected: boolean }> = [];

  ngOnInit(){

    const seatsPageable: SeatsPageable = { page: 0, size: 15 };

    this.apiService.allSeats(seatsPageable).subscribe({
      next: (response) => {
       
        this.seats = response._embedded.seats.map(seat => ({
          seatData: seat,
          selected: false
        }));
      },
      error: (error) => {
        console.error('Error fetching seats:', error);
      },
      complete: () => {
        console.log('Finished fetching seats.');
      }
    });

  }


  toggleSeat(seat: any) { 
    
    seat.selected = !seat.selected;
    const url = seat.seatData._links.self.href
    const id = url.split("seat/")[1];
    console.log(id); 
  }

}
