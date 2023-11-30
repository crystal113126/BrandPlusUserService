package com.example.BrandPlusRegistrationService.controller;

import com.example.BrandPlusRegistrationService.model.ProfileData;
import com.example.BrandPlusRegistrationService.model.request.SignInRequest;
import com.example.BrandPlusRegistrationService.model.request.SignInWithGoogleRequest;
import com.example.BrandPlusRegistrationService.model.request.SignUpRequest;
import com.example.BrandPlusRegistrationService.model.response.SignInResponse;
import com.example.BrandPlusRegistrationService.service.BrandPlusSignUpService;
import com.example.BrandPlusRegistrationService.service.LoadProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private BrandPlusSignUpService signUpService;
    @Autowired
    private LoadProfileDataService loadProfileDataService;

    @PostMapping(path="/register")
    ResponseEntity<String> createUser(@RequestBody SignUpRequest request) {
        if (signUpService.findByEmail(request.getEmail()) != null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{}");
        else
            return ResponseEntity.ok(signUpService.createAccountInfo(request));

    }

    @PostMapping(path="/login")
    public ResponseEntity<SignInResponse> loginUser(@RequestBody SignInRequest request) {
        SignInResponse signInResponse = signUpService.loginUser(request);
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping(path="/googlesignin")
    public ResponseEntity<SignInResponse> signInWithGoogle(@RequestBody SignInWithGoogleRequest request) {
        SignInResponse signInResponse = signUpService.signInWithGoogle(request);
        return ResponseEntity.ok(signInResponse);
    }


    @GetMapping("/profile")
    public ProfileData readProfileData(@RequestParam String uuid){
        return loadProfileDataService.loadProfileData(uuid);
    }
}



