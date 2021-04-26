package com.als.score.api;

import com.als.score.entity.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(@Tag(name = "manager", description = "管理员接口"))
public interface ManagerApi {

    @Operation(summary = "获取管理员信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
    })
    @GetMapping("/{managerId}")
    public ResponseEntity<Manager> getManager(@PathVariable(value = "managerId", required = true)String managerId);

    @Operation(summary = "创建学生权限", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "创建成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "创建失败")
    })
    @Parameters()
    @PostMapping("/students")
    public ResponseEntity<String> createStudents(@RequestBody List<Student> students);

    @Operation(summary = "创建教师权限", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "创建成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "创建失败")
    })
    @PostMapping("/teachers")
    public ResponseEntity<String> createTeachers(@RequestBody List<Teacher> teachers);

    @Operation(summary = "创建课程", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "创建成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "创建失败")
    })
    @PostMapping("/courses")
    public ResponseEntity<String> createCourses(@RequestBody List<Course> courses);

    @Operation(summary = "学生选课", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "选课成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "选课失败")
    })
    @PostMapping("/scores")
    public ResponseEntity<String> createScore(@RequestBody List<Score> scores);

    @Operation(summary = "修改学生权限", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "修改失败")
    })
    @PutMapping("/students")
    public ResponseEntity<String> updateStudents(@RequestBody List<Student> students);

    @Operation(summary = "修改教师权限", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "修改失败")
    })
    @PutMapping("/teachers")
    public ResponseEntity<String> updateTeachers(@RequestBody List<Teacher> teachers);

    @Operation(summary = "修改课程", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "修改失败")
    })
    @PutMapping("/courses")
    public ResponseEntity<String> updateCourses(@RequestBody List<Course> courses);

    @Operation(summary = "修改学生选课信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "修改失败")
    })
    @PutMapping("/scores")
    public ResponseEntity<String> updateScore(@RequestBody List<Score> scores);

    @Operation(summary = "删除学生信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "删除失败")
    })
    @DeleteMapping("/student")
    public ResponseEntity<String> delStudent(@RequestParam("studentId")String studentId);

    @Operation(summary = "删除教师信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "409", description = "删除失败")
    })
    @DeleteMapping("/teacher")
    public ResponseEntity<String> delTeacher(@RequestParam("teacherId")String teacherId);


    @Operation(summary = "获取课程信息", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "401", description = "用户未登录"),
            @ApiResponse(responseCode = "403", description = "权限不足"),
            @ApiResponse(responseCode = "404", description = "课程不存在")
    })
    @GetMapping("/course")
    public ResponseEntity<Course> getCourse(String courseId);
}
