package com.als.score.api;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Teacher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "teacher", description = "教师接口")
public interface TeacherApi {
    @Operation(summary = "通过教师号获取教师信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
    })
    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable(value = "teacherId", required = true)String teacherId);

    @Operation(summary = "修改成绩信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "更新失败")
    })
    @PutMapping(value = "/scores")
    public ResponseEntity<String> updateScore(@RequestBody List<ScoreDetails> scores);

    @Operation(summary = "通过教师号获取学生成绩信息", description = "可指定确认的年份学期获取对应时间内的成绩", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "404", description = "成绩不存在")
    })
    @GetMapping("/{teacherId}/scores")
    public ResponseEntity<List<ScoreDetails>> getTeacherScores(
            @Parameter(description = "教师号", in = ParameterIn.PATH) @Valid @PathVariable(value = "teacherId", required = true)String teacherId,
            @Parameter(description = "课程号", in = ParameterIn.QUERY) @Valid @RequestParam(value = "courseId", required = true)String courseId,
            @Parameter(description = "年级", in = ParameterIn.QUERY) @Valid @RequestParam(value = "grade", required = false)String grade,
            @Parameter(description = "班级", in = ParameterIn.QUERY) @Valid @RequestParam(value = "studentClass", required = false)String studentClass
    );
}
