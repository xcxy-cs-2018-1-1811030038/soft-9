package com.als.score.dto;

import com.als.score.enums.UserType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Schema(description = "用户登录参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @Schema(description = "用户类型")
    private String userType;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "密码")
    @Size(min = 6, max = 26)
    private String password;
}
