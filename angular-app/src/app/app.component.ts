import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SeatSelectorComponent } from "./seat-selector/seat-selector.component";
import { NavbarComponent } from "./navbar/navbar.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-app';
}
