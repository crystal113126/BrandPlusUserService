package com.example.BrandPlusRegistrationService.controller;

import com.example.BrandPlusRegistrationService.model.UserData;
import com.example.BrandPlusRegistrationService.model.request.SignUpRequest;
import com.example.BrandPlusRegistrationService.service.BrandPlusSignUpService;
import com.example.BrandPlusRegistrationService.service.LoadTtkAccessService;
import com.example.BrandPlusRegistrationService.service.LoadTtkHealthTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@CrossOrigin
public class BrandPlusSignInController {
    @Autowired
    private BrandPlusSignUpService bpSignUpService;

    @Autowired
    private LoadTtkAccessService loadTtkAccessService;

    @Autowired
    private LoadTtkHealthTrackerService loadTtkHealthTrackerService;



    @GetMapping("/createAdminAcc")
    public String createAdminAccount(@RequestBody SignUpRequest request){
        return bpSignUpService.createAdminAccountInfo(request);
    }

    @GetMapping("/signIn")
    public String brandPlusSignIn(@RequestParam String email, @RequestParam String password){
        return bpSignUpService.checkAccountInfo(email,password);
    }

    @PostMapping("/linkTikTok")
    public boolean linkTikTok(@RequestParam String authCode, @RequestParam String uuid){
        //loadTtkAccessService.loadAccessByAuth(authCode, uuid);
        loadTtkHealthTrackerService.LoadTtkHealthTracker(uuid);
        return true;
    }

    @PostMapping("/resetPassword")
    public void resetPassword(@RequestParam String uuid, @RequestParam String newPassword){
        bpSignUpService.updateAccountInfo(uuid, newPassword);
    }

}
