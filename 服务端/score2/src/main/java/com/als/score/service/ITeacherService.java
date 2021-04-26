package com.als.score.service;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
public interface ITeacherService extends IService<Teacher> {
    public boolean updateScores(List<ScoreDetails> scores);
}
