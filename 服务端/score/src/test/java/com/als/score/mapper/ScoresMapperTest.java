package com.als.score.mapper;

import java.util.List;

import com.als.score.entity.StudentScores;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ScoresMapperTest {

    @Autowired
    private ScoresMapper scoresMapper;

    @Test
    public void getScoress() {
        List<StudentScores> scores = scoresMapper.selectStudentScores3(100001, "2018", "1");
        log.debug("score:{}",scores);
    }
}