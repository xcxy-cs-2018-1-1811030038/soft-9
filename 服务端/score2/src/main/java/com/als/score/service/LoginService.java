package com.als.score.service;

import com.als.score.dto.UserForm;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    public ResponseEntity<Map<String, Object>> login(UserForm userForm);
}
