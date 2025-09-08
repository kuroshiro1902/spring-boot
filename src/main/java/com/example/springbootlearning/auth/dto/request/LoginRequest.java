package com.example.springbootlearning.auth.dto.request;

import com.example.springbootlearning.common.deserializer.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest (
    @NotBlank(message = "username must not be blank")
    @Size(min = 5, max = 50, message = "Username length must be between 5 and 50")
    @JsonDeserialize(using = TrimStringDeserializer.class)
    String username,

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 100, message = "Password length must be between 8 and 100")
    @JsonDeserialize(using = TrimStringDeserializer.class)
    String password
){
}
