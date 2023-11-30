package com.example.BrandPlusRegistrationService.repository;

import com.example.BrandPlusRegistrationService.model.UserData;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface RetrieveAccDataMyBatisRepository {
    @Select("SELECT email FROM account_info WHERE user_id = #{uuid}")
    public String getEmail(@NotBlank String uuid);
    @Select("SELECT * FROM account_info WHERE user_id = #{uuid}")
    public UserData getUser(String uuid);

    @Select("SELECT ref_time_stamp FROM tiktok_auth WHERE user_id = #{uuid}")
    public Long getTtkRefExp(@NotBlank String uuid);

    @Select("SELECT access_token FROM tiktok_auth WHERE user_id = #{uuid}")
    public String getTtkAccessToken(@NotBlank String uuid);

    @Select("SELECT business_id FROM tiktok_auth WHERE user_id = #{uuid}")
    public String getTtkBusinessId(@NotBlank String uuid);

}
