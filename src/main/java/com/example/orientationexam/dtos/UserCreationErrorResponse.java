package com.example.orientationexam.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreationErrorResponse implements UserCreationResponse {
    private String error;
}
