package com.als.score.api;

import com.als.score.dto.UserForm;
import com.als.score.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginApiController implements LoginApi{

    @Autowired
    private LoginService loginService;

    @Override
    public ResponseEntity<Map<String, Object>> login(@Valid UserForm userForm) {
        log.debug(userForm.toString());
        return loginService.login(userForm);
    }
}
