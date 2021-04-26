package com.als.score.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户类型
 */
@Getter
@AllArgsConstructor
public enum UserType {
    // 学生
    STUDENT("student"),
    // 教师
    TEACHER("teacher"),
    // 管理员
    MANAGER("manager");

    private String value;
}
