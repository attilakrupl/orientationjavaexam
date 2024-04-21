package com.example.orientationexam.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreationSuccessResponse implements UserCreationResponse {
    private String name;
    private Long id;
}
