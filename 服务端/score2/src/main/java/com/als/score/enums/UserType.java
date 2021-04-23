package com.als.score.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    STUDENT("student"),
    TEACHER("teacher"),
    MANAGER("manager");

    private String value;


}
