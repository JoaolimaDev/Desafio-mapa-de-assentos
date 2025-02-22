package com.fibbo.spring_app.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BookingDTO(

    @NotBlank(message = "Por favor, preencha o campo userName.")
    String userName,

    @NotBlank(message = "Por favor, preencha o campo seatId.")
    String seatId,

    @NotBlank(message = "Por favor, preencha o campo status.")
    @Pattern(regexp = "ALOCACAO|REMOCAO", message = "A acao deve ser 'ALOCACAO' ou 'REMOCAO'.")
    String acao
) {}
