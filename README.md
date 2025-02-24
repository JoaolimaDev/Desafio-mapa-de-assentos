# Desafio-mapa-de-assentos


Este projeto é uma aplicação completa para gerenciamento de assentos, focada em um sistema de reserva. A ideia central é oferecer uma interface robusta e moderna que integra funcionalidades essenciais para o controle, permitindo a comunicação eficiente entre o front-end e o back-end, com política de segurança utilizando Json web token (JWT).



- **Comunicação via API:**  
  A comunicação entre o front-end e o back-end é realizada por meio de uma API REST.  
  - **Back-end:** Desenvolvido com Java e Spring Boot.  
  - **Front-end:** Desenvolvido com Angular , utilizando Guards para proteção de rotas.
 
- **Tecnologias utilizadas:**
  -  JAVA 17.0.14
  -  DOCKER 27.5.1
  -  ANGULAR 19.1.6
  -  SPRING BOOT 3.4.2
  -  POSTGRESQL
  -  SWAGGER


| Endpoint                          | URL                                                   | Operações                                                                                                                                           |
|-----------------------------------|-------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| `/api/booking/`                   | `http://localhost:8080/api/booking/`                    | **GET** (listBookings): lista bookings com paginação<br>**PUT** (updateBooking): atualiza um booking (parâmetro `id` via query e body JSON)<br>**POST** (createBooking): cria um novo booking (body JSON)<br>**DELETE** (deleteBooking): remove um booking (parâmetro `id` via query) |
| `/api/auth/logout`                | `http://localhost:8080/api/auth/logout`               | **POST** (logout): encerra a sessão do usuário                                                                                                     |
| `/api/auth/login`                 | `http://localhost:8080/api/auth/login`                | **POST** (login): realiza o login (body JSON com LoginRequest)                                                                                     |
| `/api/seat/`                      | `http://localhost:8080/api/seat/`                     | **GET** (getSeats): retorna a lista de seats                                                                                                        |
| `/api/booking/{id}`               | `http://localhost:8080/api/booking/{id}`              | **GET** (getBookingById): busca um booking específico pelo `id` informado na URL                                                                   |
| `/api/booking/findbySeat/{id}`    | `http://localhost:8080/api/booking/findbySeat/{id}`   | **GET** (findbySeat): busca um booking pelo `id` do seat (parâmetro informado via query, apesar da indicação na URL)                                   |



## Como utilizar
  - Certifique-se que o seu docker local detém permissões a nível sudo

```bash
    git clone https://github.com/JoaolimaDev/Desafio-mapa-de-assentos.git
    docker compose up --build
    cd angular-app
    ng serve
```


**Usuário disponível para autênticação**
- ** email: user1**
- ** senha  user123**


- ** email: user2**
- ** senha  user1234**


<p align="left">
  💌 Contatos: ⤵️
</p>

<p align="left">
  <a href="mailto:ozymandiasphp@gmail.com" title="Gmail">
  <img src="https://img.shields.io/badge/-Gmail-FF0000?style=flat-square&labelColor=FF0000&logo=gmail&logoColor=white&link=LINK-DO-SEU-GMAIL" alt="Gmail"/></a>
  <a href="https://www.linkedin.com/in/jo%C3%A3o-vitor-de-lima-74441b1b1/" title="LinkedIn">
  <img src="https://img.shields.io/badge/-Linkedin-0e76a8?style=flat-square&logo=Linkedin&logoColor=white&link=LINK-DO-SEU-LINKEDIN" alt="LinkedIn"/></a>
  <a href="https://wa.me/5581989553431" title="WhatsApp">
  <img src="https://img.shields.io/badge/-WhatsApp-25d366?style=flat-square&labelColor=25d366&logo=whatsapp&logoColor=white&link=API-DO-SEU-WHATSAPP" alt="WhatsApp"/></a>
</p>
