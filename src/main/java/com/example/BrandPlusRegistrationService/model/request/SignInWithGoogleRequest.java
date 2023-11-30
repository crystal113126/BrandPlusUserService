package com.example.BrandPlusRegistrationService.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInWithGoogleRequest {
    private String name;
    private String email;
    private String token;
}


