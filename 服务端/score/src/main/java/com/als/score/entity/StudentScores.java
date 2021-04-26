package com.als.score.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 学生成绩表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentScores{
    private Integer score;
    private Integer courseId;
    private Integer teacherId;
    private Integer studentId;
    private String courseName;
    private String teacherName;
    private String year;
    private String term;
    private LocalDateTime updateTime;
}
