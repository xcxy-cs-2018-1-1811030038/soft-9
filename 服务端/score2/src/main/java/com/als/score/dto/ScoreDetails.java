package com.als.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Schema(description = "成绩信息", title = "Score")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDetails {
    @Schema(description = "学号")
    private String studentId;
    @Schema(description = "学生姓名")
    private String studentName;
    @Schema(description = "教师号")
    private String teacherId;
    @Schema(description = "教师姓名")
    private String teacherName;
    @Schema(description = "课程号")
    private String courseId;
    @Schema(description = "课程名")
    private String courseName;
    @Schema(description = "年份")
    private String year;
    @Schema(description = "学期")
    private String term;
    @Min(-1)
    @Max(100)
    @Schema(description = "成绩，-1代表未输入成绩")
    private Integer score;
}
