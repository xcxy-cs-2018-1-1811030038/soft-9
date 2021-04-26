package com.als.score.mapper;

import java.util.List;

import com.als.score.entity.Scores;
import com.als.score.entity.StudentScores;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * mybatis不支持方法重载
 * @author aion
 * @since 2021-03-10
 */
@Mapper
public interface ScoresMapper extends BaseMapper<Scores> {

    /**
     * 三表联查，通过学生id查询成绩
     * @param id
     * @return
     */
    @Select("SELECT s.*, c.*, t.* FROM scores s	LEFT JOIN teachers t ON s.`teacher_id` = t.teacher_id	LEFT JOIN courses c ON c.`course_id` = s.`course_id` WHERE s.`student_id` = #{id}")
    List<StudentScores> selectStudentScores1(@Param("id")Integer id);

    /**
     * 加上确定年份查询成绩
     * @param id
     * @param year
     * @return
     */
    @Select("SELECT s.*, c.*, t.* FROM scores s	LEFT JOIN teachers t ON s.`teacher_id` = t.teacher_id	LEFT JOIN courses c ON c.`course_id` = s.`course_id` WHERE s.`student_id` = #{id} and c.year = #{year}")
    List<StudentScores> selectStudentScores2(@Param("id")Integer id, @Param("year")String year);

    /**
     * 加上确定学期查询成绩
     * @param id
     * @param year
     * @param term
     * @return
     */
    @Select("SELECT s.*, c.*, t.* FROM scores s	LEFT JOIN teachers t ON s.`teacher_id` = t.teacher_id	LEFT JOIN courses c ON c.`course_id` = s.`course_id` WHERE s.`student_id` = #{id} and c.year = #{year} and c.term = #{term}")
    List<StudentScores> selectStudentScores3(@Param("id")Integer id, @Param("year")String year, @Param("term")String term);
}
