package com.example.BrandPlusRegistrationService.repository;

import com.example.BrandPlusRegistrationService.model.UserData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountInfoMyBatisRepository {
    @Select("SELECT * FROM account_info WHERE email = #{email}")
    public UserData findByEmail(@NotBlank String email);

    @Delete("DELETE FROM account_info WHERE email = #{email}")
    public int deleteByEmail(@NotBlank String email);

    @Insert("INSERT INTO account_info(user_id, email, password, admin, name, status,sign_in_type, token) " +
            " VALUES (#{uuid}, #{email}, #{password}, #{admin, jdbcType=BIT}, #{name}, #{status}, #{signInType}, #{token})")
    public int insert(@NotNull UserData userData);

    @Update("UPDATE account_info SET password = #{password} WHERE user_id = #{uuid}")
    public int updatePassword(@NotBlank String uuid,@NotBlank String password);

    @Update("UPDATE account_info SET admin = #{admin, jdbcType=BIT} WHERE email = #{email}")
    public int updateRole(@NotNull UserData userData);

    @Select("SELECT admin FROM account_info WHERE email = #{email}")
    public boolean getAdmin(@NotBlank String email);

}
