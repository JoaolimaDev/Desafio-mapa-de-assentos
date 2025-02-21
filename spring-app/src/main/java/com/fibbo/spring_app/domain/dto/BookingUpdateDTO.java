package com.fibbo.spring_app.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BookingUpdateDTO(
    @NotBlank(message = "Por favor, preencha o campo status.")
    @Pattern(regexp = "ABERTA|ENCERRADA", message = "O status deve ser 'ABERTA' ou 'ENCERRADA'.")  
    String status
) {}
