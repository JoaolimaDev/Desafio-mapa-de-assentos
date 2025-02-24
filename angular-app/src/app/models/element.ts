export interface BookingResponse {
    id: number;
    user: any;      
    seat: {
      id: string;
      ocupada: boolean;
    };
    acao: string;
  }
  