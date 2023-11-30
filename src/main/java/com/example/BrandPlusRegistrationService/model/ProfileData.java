package com.example.BrandPlusRegistrationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileData {
    UserData user;
    boolean isTtkLinked;
    boolean isTtkExpired;
    boolean isGoogleLined;
    boolean isGoogleExpired;
    boolean isMetaLinked;
    boolean isMetaExpired;
}
