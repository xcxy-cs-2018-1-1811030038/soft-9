package com.als.score.mapper;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

    @Select("SELECT st.`student_id`, st.`student_name`,  t.`teacher_id`, t.`teacher_name`, c.`course_id`, c.`course_name`, c.`year`, c.`term`, se.`score`\n" +
            "\tFROM score AS se\n" +
            "\t\tLEFT JOIN student AS st ON se.`student_id` = st.`student_id`\n" +
            "\t\t\tLEFT JOIN course c ON se.`course_id` = c.`course_id`\n" +
            "\t\t\t\tLEFT JOIN teacher t ON t.`teacher_id` = c.`teacher_id`\n" +
            "\t\t\t\t\tWHERE st.`student_id`=#{studentId}")
    List<ScoreDetails> selectScoreDetailsByStuId(@Param("studentId")String studentId);

    @Select("SELECT st.`student_id`, st.`student_name`,  t.`teacher_id`, t.`teacher_name`, c.`course_id`, c.`course_name`, c.`year`, c.`term`, se.`score`\n" +
            "\tFROM score AS se\n" +
            "\t\tLEFT JOIN student AS st ON se.`student_id` = st.`student_id`\n" +
            "\t\t\tLEFT JOIN course c ON se.`course_id` = c.`course_id`\n" +
            "\t\t\t\tLEFT JOIN teacher t ON t.`teacher_id` = c.`teacher_id`\n" +
            "\t\t\t\t\tWHERE st.`student_id`=#{studentId} and c.year = #{year}")
    List<ScoreDetails> seleselectScoreDetailsByStuIdYear(@Param("studentId")String studentId, @Param("year")String year);

    @Select("SELECT st.`student_id`, st.`student_name`,  t.`teacher_id`, t.`teacher_name`, c.`course_id`, c.`course_name`, c.`year`, c.`term`, se.`score`\n" +
            "\tFROM score AS se\n" +
            "\t\tLEFT JOIN student AS st ON se.`student_id` = st.`student_id`\n" +
            "\t\t\tLEFT JOIN course c ON se.`course_id` = c.`course_id`\n" +
            "\t\t\t\tLEFT JOIN teacher t ON t.`teacher_id` = c.`teacher_id`\n" +
            "\t\t\t\t\tWHERE st.`student_id`=#{studentId} and c.year = #{year} and c.term = #{term}")
    List<ScoreDetails> seleselectScoreDetailsByStuIdYearTerm(@Param("studentId")String studentId, @Param("year")String year, @Param("term")String term);

    @Select("SELECT st.`student_id`, st.`student_name`,  t.`teacher_id`, t.`teacher_name`, c.`course_id`, c.`course_name`, c.`year`, c.`term`, se.`score`\n" +
            "\tFROM score AS se\n" +
            "\t\tLEFT JOIN student AS st ON se.`student_id` = st.`student_id`\n" +
            "\t\t\tLEFT JOIN course c ON se.`course_id` = c.`course_id`\n" +
            "\t\t\t\tLEFT JOIN teacher t ON t.`teacher_id` = c.`teacher_id`\n" +
            "\t\t\t\t\tWHERE t.`teacher_id`=#{teacherId} and c.course_id = #{courseId}")
    List<ScoreDetails> selectScoreDetailsByTeacherIdCourseId(@Param("teacherId")String teacherId, @Param("courseId")String courseId);

    @Select("SELECT st.`student_id`, st.`student_name`,  t.`teacher_id`, t.`teacher_name`, c.`course_id`, c.`course_name`, c.`year`, c.`term`, se.`score`\n" +
            "\tFROM score AS se\n" +
            "\t\tLEFT JOIN student AS st ON se.`student_id` = st.`student_id`\n" +
            "\t\t\tLEFT JOIN course c ON se.`course_id` = c.`course_id`\n" +
            "\t\t\t\tLEFT JOIN teacher t ON t.`teacher_id` = c.`teacher_id`\n" +
            "\t\t\t\t\tWHERE t.`teacher_id`=#{teacherId} and st.grade = #{grade} and c.course_id = #{courseId}")
    List<ScoreDetails> seleselectScoreDetailsByTeacherIdGradeCourseId(@Param("teacherId")String teacherId, @Param("grade")String grade, @Param("courseId")String courseId);

    @Select("SELECT st.`student_id`, st.`student_name`,  t.`teacher_id`, t.`teacher_name`, c.`course_id`, c.`course_name`, c.`year`, c.`term`, se.`score`\n" +
            "\tFROM score AS se\n" +
            "\t\tLEFT JOIN student AS st ON se.`student_id` = st.`student_id`\n" +
            "\t\t\tLEFT JOIN course c ON se.`course_id` = c.`course_id`\n" +
            "\t\t\t\tLEFT JOIN teacher t ON t.`teacher_id` = c.`teacher_id`\n" +
            "\t\t\t\t\tWHERE t.`teacher_id`=#{teacherId} and st.grade = #{grade} and st.student_class = #{studentClass} and c.course_id = #{courseId}")
    List<ScoreDetails> seleselectScoreDetailsByTeacherIdGradeClassCourseId(@Param("teacherId")String teacherId, @Param("grade")String grade, @Param("studentClass")String studentClass, @Param("courseId")String courseId);
}
