package com.als.score.service.impl;

import java.util.List;

import com.als.score.entity.Scores;
import com.als.score.entity.StudentScores;
import com.als.score.mapper.ScoresMapper;
import com.als.score.service.IScoresService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aion
 * @since 2021-03-10
 */
@Service
public class ScoresServiceImpl extends ServiceImpl<ScoresMapper, Scores> implements IScoresService {
    @Autowired
    private ScoresMapper scoresMapper;

    
    /**
     * 学生获取所有课程成绩
     * @param id
     * @return
     */
    public List<StudentScores> getScoresStuById(Integer id) {
        List<StudentScores> list = scoresMapper.selectStudentScores1(id);
        return list;
    }
    /**
     * 学生获取特定年份所有学期的课程成绩
     * @param id
     * @param year
     * @return
     */
    public List<StudentScores> getScoresStuById(Integer id, String year) {
        List<StudentScores> list = scoresMapper.selectStudentScores2(id, year);
        return list;
    }
    /**
     * 学生获取特定年份、学期所有课程成绩
     * @param id
     * @param year
     * @param term
     * @return
     */
    public List<StudentScores> getScoresStuById(Integer id, String year, String term) {
        List<StudentScores> list = scoresMapper.selectStudentScores3(id, year, term);
        return list;
    }
}
