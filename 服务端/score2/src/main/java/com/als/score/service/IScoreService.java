package com.als.score.service;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
public interface IScoreService extends IService<Score> {

    /**
     * 根据学号获取成绩
     * @param studentId
     * @param year 指定年份
     * @param term 指定学期
     * @return
     */
    public List<ScoreDetails> getScoresByStudentId(String studentId, @Nullable String year, @Nullable String term);

    /**
     * 根据教师号获取成绩
     * @param teacherId
     * @param year
     * @param term
     * @return
     */
    public List<ScoreDetails> getScoresByTeacherId(String teacherId, @Nullable String year, @Nullable String term, String courseId);
}
