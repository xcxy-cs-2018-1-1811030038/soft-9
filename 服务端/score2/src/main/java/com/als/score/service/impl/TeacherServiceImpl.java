package com.als.score.service.impl;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Score;
import com.als.score.entity.Teacher;
import com.als.score.mapper.ScoreMapper;
import com.als.score.mapper.TeacherMapper;
import com.als.score.service.ITeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
@Slf4j
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    @Transactional
    public boolean updateScores(List<ScoreDetails> scores) {
        try {
            // 逐条更新
            for (ScoreDetails score: scores) {
                Score scoreEntity = new Score(null, score.getStudentId(), score.getCourseId(), score.getScore());
                if (-1 != scoreMapper.update(scoreEntity, new UpdateWrapper<Score>().eq("student_id", scoreEntity.getStudentId()).eq("course_Id", scoreEntity.getCourseId()).set(true, "score", scoreEntity.getScore())))    {
                    log.info("{}更新成功", scoreEntity.getStudentId());
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
