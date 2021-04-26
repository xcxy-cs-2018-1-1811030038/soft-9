package com.als.score.api;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Teacher;
import com.als.score.service.IScoreService;
import com.als.score.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController implements TeacherApi{
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IScoreService scoreService;


    @Override
    public ResponseEntity<Teacher> getTeacher(String teacherId) {
        Teacher teacher = teacherService.getById(teacherId);

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateScore(List<ScoreDetails> scores) {
        if (teacherService.updateScores(scores)) {
            return new ResponseEntity<>("更新成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("更新失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<List<ScoreDetails>> getTeacherScores(@Valid String teacherId, @Valid String courseId, @Valid String grade, @Valid String studentClass) {
        List<ScoreDetails> scores = scoreService.getScoresByTeacherId(teacherId, grade, studentClass, courseId);

        // 判断成绩是否存在
        if (scores != null) {
            return new ResponseEntity<>(scores, HttpStatus.ACCEPTED.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
