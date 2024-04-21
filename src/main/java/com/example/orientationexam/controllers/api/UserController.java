package com.example.orientationexam.controllers.api;

import com.example.orientationexam.dtos.UserCreationRequest;
import com.example.orientationexam.dtos.UserCreationErrorResponse;
import com.example.orientationexam.dtos.UserCreationResponse;
import com.example.orientationexam.dtos.UserCreationSuccessResponse;
import com.example.orientationexam.models.User;
import com.example.orientationexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public ResponseEntity<UserCreationResponse> postUsers(@RequestBody UserCreationRequest request) {

        if(request == null || request.getName().isEmpty()){
            UserCreationErrorResponse response = new UserCreationErrorResponse();
            response.setError("Missing name");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User savedUser = userService.saveUser(request.getName());
        UserCreationSuccessResponse response = new UserCreationSuccessResponse();
        response.setName(savedUser.getName());
        response.setId(savedUser.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
