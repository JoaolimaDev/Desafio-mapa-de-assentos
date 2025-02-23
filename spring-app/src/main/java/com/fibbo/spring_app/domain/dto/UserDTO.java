package com.fibbo.spring_app.domain.dto;

import com.fibbo.spring_app.domain.model.User;

public record UserDTO(String username) {
    public UserDTO(User user) {
        this(user.getUsername());
    }
}
