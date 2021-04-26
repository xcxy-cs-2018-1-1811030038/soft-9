package com.als.score.api;

import com.als.score.dto.ScoreDetails;
import com.als.score.entity.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "studnet", description = "学生接口")
public interface StudentApi {

    @Operation(summary = "通过学号获取学生成绩信息", description = "可指定确认的年份学期获取对应时间内的成绩", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "404", description = "成绩不存在")
    })
    @GetMapping("/{studentId}/scores")
    public ResponseEntity<List<ScoreDetails>> getStudentScores(
            @Parameter(description = "学号", in = ParameterIn.PATH) @Valid @PathVariable(value = "studentId", required = true)String studentId,
            @Parameter(description = "年份", in = ParameterIn.QUERY) @Valid @RequestParam(value = "year", required = false)String year,
            @Parameter(description = "学期", in = ParameterIn.QUERY) @Valid @RequestParam(value = "term", required = false)String term
    );

    @Operation(summary = "通过学号获取学生信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
    })

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "studentId", required = true)String studentId);
}
