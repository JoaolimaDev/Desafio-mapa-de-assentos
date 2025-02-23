export interface Seat {
    ocupada: boolean;
    _links: {
      self: { href: string };
      seat: { href: string };
    };
  }
  
  export interface SeatResponse {
    _embedded: {
      seats: Seat[];
    };
    _links: {
      self: { href: string };
      profile: { href: string };
    };
    page: {
      size: number;
      totalElements: number;
      totalPages: number;
      number: number;
    };
  }
  