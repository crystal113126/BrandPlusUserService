package com.example.BrandPlusRegistrationService.service;

import com.example.BrandPlusRegistrationService.model.ProfileData;
import com.example.BrandPlusRegistrationService.repository.RetrieveAccDataMyBatisRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadProfileDataService {

    @Autowired
    private RetrieveAccDataMyBatisRepository repository;
    public ProfileData loadProfile(@NotBlank String uuid){
        return new ProfileData().setUser(repository.getUser(uuid)).setGoogleExpired(false)
                                                   .setGoogleLined(false)
                                                   .setMetaExpired(false)
                                                   .setMetaLinked(false)
                                                   .setTtkLinked(checkTtkLinked(uuid))
                                                   .setTtkExpired(checkTtkExpired(uuid));
    }

    public boolean checkTtkLinked(String uuid){
        return repository.getTtkRefExp(uuid) != null;
    }

    public boolean checkTtkExpired(String uuid){
        Long currTime = System.currentTimeMillis() / 1000;
        return !checkTtkLinked(uuid) || repository.getTtkRefExp(uuid) < currTime;
    }

    public ProfileData loadProfileData(String uuid) {
        return new ProfileData().setUser(repository.getUser(uuid)).setGoogleExpired(false)
                .setGoogleLined(false)
                .setMetaExpired(false)
                .setMetaLinked(false)
                .setTtkLinked(checkTtkLinked(uuid))
                .setTtkExpired(checkTtkExpired(uuid));
    }

}
