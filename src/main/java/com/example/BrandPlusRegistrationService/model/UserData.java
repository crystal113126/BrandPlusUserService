package com.example.BrandPlusRegistrationService.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@Scope("prototype")
@Accessors(chain = true)
public class UserData {
    private String uuid;

    @NotBlank(message = "Email cannot be blank")
    private String email;
    private String password;

    private boolean admin;
    private String name;
    private String status;
    private String signInType;
    private String token;

    public UserData(String uuid,  String email, String password, boolean admin,String name, String status, String signInType, String token) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.status = status;
        this.signInType = signInType;
        this.token = token;

    }

}
