package com.als.score.service;

import com.als.score.dto.ScoreDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class TeacherServiceTest {
    @Autowired
    private ITeacherService teacherService;

    @Test
    public void updateScores() {
      List<ScoreDetails> scores = new ArrayList<>();

      scores.add(new ScoreDetails("10000001", "小金", "50000001", "老赵", "500000001", "英语", "2018", "1", 80));
      scores.add(new ScoreDetails("10000002", "小金", "50000001", "老赵", "500000001", "英语", "2018", "1", 80));
      scores.add(new ScoreDetails("10000003", "小金", "50000001", "老赵", "500000001", "英语", "2018", "1", 80));

      if (teacherService.updateScores(scores)) {
          log.info("更新成功");
      } else {
          log.info("更新失败");

      }
    }
}
