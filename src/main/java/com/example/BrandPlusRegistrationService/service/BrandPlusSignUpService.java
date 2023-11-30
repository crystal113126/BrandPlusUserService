package com.example.BrandPlusRegistrationService.service;

import com.example.BrandPlusRegistrationService.model.UserData;
import com.example.BrandPlusRegistrationService.model.request.SignInRequest;
import com.example.BrandPlusRegistrationService.model.request.SignInWithGoogleRequest;
import com.example.BrandPlusRegistrationService.model.request.SignUpRequest;
import com.example.BrandPlusRegistrationService.model.response.SignInResponse;
import com.example.BrandPlusRegistrationService.repository.AccountInfoMyBatisRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BrandPlusSignUpService {

    @Autowired
    private AccountInfoMyBatisRepository accountInfoMyBatisRepository;


    private final PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16,32,8,65536,4);

    public boolean insertAccountInfo(UserData userData){
        if (userData.getPassword() == null) {
            accountInfoMyBatisRepository.insert(userData);
            return true;
        }
        try {
            String hashedPassword = passwordEncoder.encode(userData.getPassword());
            userData.setPassword(hashedPassword);
            accountInfoMyBatisRepository.insert(userData);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateAccountInfo(@NotBlank String uuid, @NotBlank String password){
        try {
            String hashedPassword = passwordEncoder.encode(password);
            accountInfoMyBatisRepository.updatePassword(uuid,hashedPassword);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public String checkAccountInfo(String email, String password){
        UserData storedData = accountInfoMyBatisRepository.findByEmail(email);
        if(storedData == null){
            return null;
        }
        if(passwordEncoder.matches(password, storedData.getPassword())){
           return storedData.getUuid();
        }
        return null;
    }

    public UserData findByEmail(String email) {
        return accountInfoMyBatisRepository.findByEmail(email);
    }

    public String createAccountInfo(SignUpRequest request) {
        UserData user = new UserData(
                UUID.randomUUID().toString(),
                request.getEmail(),
                request.getPassword(),
                false,
                request.getName(),
                "notVerified",
                "email",
                null
        );
        insertAccountInfo(user);
        return "User Created Successfully!";
    }

    public String createAdminAccountInfo(SignUpRequest request) {
        UserData user = new UserData(
                UUID.randomUUID().toString(),
                request.getEmail(),
                request.getPassword(),
                true,
                request.getName(),
                "Verified",
                "email",
                null
        );
        insertAccountInfo(user);
        return "Admin Created Successfully!";
    }

    public SignInResponse loginUser(SignInRequest request) {
        UserData storedData = accountInfoMyBatisRepository.findByEmail(request.getEmail());
        if (storedData != null) {
            if(passwordEncoder.matches(request.getPassword(), storedData.getPassword())){
                System.out.println(storedData);
                return new SignInResponse( "Login Success", storedData.getUuid(), storedData.getName());
            } else {
                return new SignInResponse ("Password Not Match", null, null);
            }
        }else {
            return new SignInResponse("Email not existed", null, null);
        }

    }

    public SignInResponse signInWithGoogle(SignInWithGoogleRequest request) {
        UserData storedData = accountInfoMyBatisRepository.findByEmail(request.getEmail());
        if (storedData == null) {
            UserData user = new UserData(
                    UUID.randomUUID().toString(),
                    request.getEmail(),
                    null,
                      false,
                    request.getName(),
                    "notVerified",
                    "google",
                    request.getToken()
            );
            insertAccountInfo(user);
            storedData = accountInfoMyBatisRepository.findByEmail(request.getEmail());
        }
        return new SignInResponse( "Login Success", storedData.getUuid(), storedData.getName());

    }
}
