package com.fibbo.spring_app.domain.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record BookingDTO(
    @NotBlank(message = "Por favor, preencha o campo status.")
    @Pattern(regexp = "ABERTA|ENCERRADA", message = "O status deve ser 'ABERTA' ou 'ENCERRADA'.") String status,
    @NotEmpty(message = "Por favor, informe ao menos um assento.") List<String> seats
){}
