import { SeatsPageable } from './../models/seats-pageable';
import { ApiService } from './../services/api.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Seat } from '../models/seat-response';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-seat-selector',
  imports: [MatIconModule, CommonModule, MatTooltipModule],
  templateUrl: './seat-selector.component.html',
  styleUrl: './seat-selector.component.css'
})
export class SeatSelectorComponent {

  constructor(private apiService: ApiService, 
    private snackBar: MatSnackBar
  ) {}

  seats: Array<{ seatData: Seat; selected: boolean, tooltipText?: any }> = [];

  ngOnInit(): void {

    this.apiService.allSeats().subscribe({
      next: (seats: Seat[]) => {
        this.seats = seats.map(seat => ({
          seatData: seat,
          selected: seat.ocupada,
          tooltipText: null
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

    let id: string;
    if (seat.selected) {
      id = seat.seatData.id;
      console.log('ID (selecionado):', id);
      this.apiService.bookingSeat(id, seat.selected);
    } else {
      this.apiService.findbySeat(seat.seatData.id).subscribe(response => {
        id = response.id.toString();
        console.log('ID (API):', id);
        
        this.apiService.bookingSeat(id, seat.selected);
      });
    }
  
  }

  mouseOver(seat: any): void {
    if (seat.selected) {
      this.apiService.findbySeat(seat.seatData.id).subscribe(response => {
        seat.tooltipText = `AÇÃO: ${response.acao} - ID ALOCACAO: ${response.id} - USUARIO: ${response.user.username}
        - ID ASSENTO: ${response.seat.id}`;
        console.log(response);
      });
    }
  }
  
}  