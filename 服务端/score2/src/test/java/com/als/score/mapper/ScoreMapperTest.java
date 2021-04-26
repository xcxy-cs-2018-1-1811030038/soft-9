package com.als.score.mapper;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Score;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class ScoreMapperTest {
    @Autowired
    private ScoreMapper scoreMapper;

    @Test
    public void getScoredetailsByStuId() {
        List<ScoreDetails> scores1 = scoreMapper.selectScoreDetailsByStuId("10000001");
        List<ScoreDetails> scores2 = scoreMapper.seleselectScoreDetailsByStuIdYear("10000001", "2018");
        List<ScoreDetails> scores3 = scoreMapper.seleselectScoreDetailsByStuIdYearTerm("10000001", "2018", "1");
        log.info("scores1: {}", scores1);
        log.info("scores2: {}", scores2);
        log.info("scores3: {}", scores3);
    }

    @Test
    public void updateScore() {
        Score score = new Score(1, "10000001", "500000001", 70);
        int i = scoreMapper.update(score, new UpdateWrapper<Score>().eq("student_id", score.getStudentId()).eq("course_id", score.getCourseId()).set(true, "score", score.getScore()));
//        int i = scoreMapper.updateById(score);
        log.info("更新情况：{}", i);
    }

    @Test
    public void createScore(){
        Score score = new Score();
        score.setStudentId("10000001");
        score.setCourseId("500000001");
        score.setScore(70);
        scoreMapper.insert(score);

    }

    @Test
    void getScoreByCourseId() {
        List<ScoreDetails> scores = scoreMapper.seleselectScoreDetailsByTeacherIdGradeClassCourseId("50000001", "2018", "1", "500000001");
        log.info("scores:{}", scores);
    }
}
