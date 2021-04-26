package com.als.score.mapper;

import com.als.score.entity.Courses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CoursesMapperTest {
    
    @Autowired
    private CoursesMapper coursesMapper;

    @Test
    public void getCourseById() {
        Courses course = coursesMapper.selectById("11111111");
        log.debug("课程:{}", course);
    }
}
