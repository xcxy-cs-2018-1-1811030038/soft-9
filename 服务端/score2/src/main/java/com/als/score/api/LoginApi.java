package com.als.score.api;

import com.als.score.dto.UserForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;


@Tag(name = "登录")
public interface LoginApi {

    @PostMapping("/{userType}")
    @Operation(description = "登录")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "登录失败"),
            @ApiResponse(responseCode = "200", description = "登录成功")
    })
    @Parameters(value = {
            @Parameter(name = "userType", in = ParameterIn.PATH, required = true, schema = @Schema(allowableValues = {"student", "teacher", "manager"})),
            @Parameter(name = "account", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "password", in = ParameterIn.QUERY, required = true)
    })
    public ResponseEntity<Map<String, Object>> login(
             @Valid UserForm userForm
    );
}
