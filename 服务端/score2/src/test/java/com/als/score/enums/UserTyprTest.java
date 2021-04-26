package com.als.score.enums;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class UserTyprTest {
    @Test
    public void setUserType() {
        UserType r = UserType.TEACHER;
        log.debug(r.getValue());
    }
}
