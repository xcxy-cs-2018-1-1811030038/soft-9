package com.als.score.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 常用响应工具包
 */
@RestController
@RequestMapping("/utils")
public class UtilsController {

    /**
     * 401
     * 用户未登录
     * @return
     */
    @RequestMapping("/unauthorized")
    public ResponseEntity<String> unauthrized() {
        return new ResponseEntity<>("用户未登录", HttpStatus.UNAUTHORIZED);
    }

    /**
     * 403
     * 权限不足
     * @return
     */
    @RequestMapping("/forbidden")
    public ResponseEntity<String> forbidden() {
        return new ResponseEntity<>("权限不足", HttpStatus.FORBIDDEN);
    }
}
