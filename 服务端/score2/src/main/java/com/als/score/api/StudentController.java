package com.als.score.api;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Student;
import com.als.score.service.IScoreService;
import com.als.score.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentApiController implements StudentApi{

    @Autowired
    private IScoreService scoreService;
    @Autowired
    private IStudentService studentService;

    @Override
    public ResponseEntity<List<ScoreDetails>> getStudentScores(@Valid String studentId, @Valid String year, @Valid String term) {
        List<ScoreDetails> scores = scoreService.getScores(studentId, year, term);

        // 判断成绩是否存在
        if (scores != null) {
            return new ResponseEntity<>(scores, HttpStatus.ACCEPTED.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Student> getStudent(String studentId) {
        Student student = studentService.getById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


}
