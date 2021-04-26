package com.als.score.service.impl;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Score;
import com.als.score.mapper.ScoreMapper;
import com.als.score.mapper.StudentMapper;
import com.als.score.mapper.TeacherMapper;
import com.als.score.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<ScoreDetails> getScoresByStudentId(String studentId, String year, String term) {
        List<ScoreDetails> scores = null;
        // 获取成绩
        if (year != null && term != null) {
            scores = scoreMapper.seleselectScoreDetailsByStuIdYearTerm(studentId, year, term);
        } else if (year != null) {
            scores = scoreMapper.seleselectScoreDetailsByStuIdYear(studentId, year);
        } else {
            scores = scoreMapper.selectScoreDetailsByStuId(studentId);
        }

        return scores;
    }

    @Override
    public List<ScoreDetails> getScoresByTeacherId(String teacherId, String grade, String studentClass, String courseId) {
        List<ScoreDetails> scores = null;
        // 获取成绩
        if (grade != null && studentClass != null) {
            scores = scoreMapper.seleselectScoreDetailsByTeacherIdGradeClassCourseId(teacherId, grade, studentClass, courseId);
        } else if (grade != null) {
            scores = scoreMapper.seleselectScoreDetailsByTeacherIdGradeCourseId(teacherId, grade, courseId);
        } else {
            scores = scoreMapper.selectScoreDetailsByTeacherIdCourseId(teacherId, courseId);
        }



        return scores;
    }

}
