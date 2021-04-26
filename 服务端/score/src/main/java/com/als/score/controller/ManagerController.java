package com.als.score.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.als.score.entity.Courses;
import com.als.score.entity.Manager;
import com.als.score.entity.Response;
import com.als.score.entity.Scores;
import com.als.score.entity.StudentScores;
import com.als.score.entity.Students;
import com.als.score.entity.Teachers;
import com.als.score.service.impl.CoursesServiceImpl;
import com.als.score.service.impl.ManagerServiceImpl;
import com.als.score.service.impl.ScoresServiceImpl;
import com.als.score.service.impl.StudentsServiceImpl;
import com.als.score.service.impl.TeachersServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aion
 * @since 2021-03-10
 */
@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private StudentsServiceImpl studentsService;
    @Autowired
    private TeachersServiceImpl teachersService;
    @Autowired
    private CoursesServiceImpl coursesService;
    @Autowired
    private ScoresServiceImpl scoresService;
    

    /**
     * 通过id获取教学管理员信息
     * @param id
     * @return
     */
    @GetMapping("/{managerId}")
    public Response getManager(@PathVariable(value = "managerId", required = true)String manaId) {
        Manager manager = managerService.getById(manaId);
        return Response.success("获取成功", manager);
    }

    /**
     * 通用账号错误返回
     * @return
     */
    @RequestMapping("/error")
    public Response reErr() {
        return Response.error("管理员账号不存在");
    }

    /**
     * 创建学生账号
     * @param student
     * @return
     */
    @PostMapping("/{managerId}/student")
    public Response createStu(Students student, @PathVariable("managerId")String manaId) {
        
        boolean flag = studentsService.save(student);
        if(flag) {
            log.debug("{}创建成功", student.getStudentName());
            return Response.success("创建成功");
        } else {
            log.debug("{}创建失败", student.getStudentName());
            return Response.error("创建失败");
        }
    }

    /**
     * 创建教师账号
     * @param student
     * @return
     */
    @PostMapping("/{managerId}/teacher")
    public Response createTea(Teachers teacher, @PathVariable("managerId")String manaId) {
        
        boolean flag = teachersService.save(teacher);
        if(flag) {
            log.debug("{}创建成功", teacher.getTeacherName());
            return Response.success("创建成功");
        } else {
            log.debug("{}创建失败", teacher.getTeacherName());
            return Response.error("创建失败");
        }
    }

    /**
     * 删除学生
     * @param manaId
     * @param stuId
     * @return
     */
    @DeleteMapping("/{managerId}/student/{studentId}")
    public Response delStu(
                        @PathVariable("managerId")String manaId,
                        @PathVariable("studentId")String stuId
    ){
        
        if(studentsService.removeById(stuId)) {
            return Response.success("删除成功");
        } else {
            return Response.error("删除失败");
        }
    }

    /**
     * 删除教师
     * @param teaId
     * @param stuId
     * @return
     */
    @DeleteMapping("/{managerId}/teacher/{teacherId}")
    public Response delTea(
                        @PathVariable("managerId")String manaId,
                        @PathVariable("teacherId")String teaId
    ){
        
        if(teachersService.removeById(teaId)) {
            return Response.success("删除成功");
        } else {
            return Response.error("删除失败");
        }
    }

    /**
     * 创建课程
     * @param course
     * @param id
     * @return
     */
    @PostMapping(value="/{managerId}/course")
    public Response createCourse(Courses course, @PathVariable("managerId")String manaId) {
        
        boolean flag = coursesService.save(course);
        if(flag) {
            return Response.success("创建成功");
        } else {
            return Response.error("创建失败");
        }
    }

    /**
     * 删除课程
     * @param manaId
     * @param courId
     * @return
     */
    @DeleteMapping(value="/{managerId}/course/{courseId}")
    public Response createCourse(
                            @PathVariable("managerId")String manaId,
                            @PathVariable("courseId")String courId
    ) {
        
        if(managerService.removeById(courId)) {
            return Response.success("删除成功");
        } else {
            return Response.error("删除失败");
        }
    }
    
    /**
     * 通过id将学生添加到课程中
     * @param manaId
     * @param stuId
     * @param score
     * @return
     */
    @PostMapping("/{managerId}/score/{studentId}")
    public Response createScore(
                            @PathVariable("managerId")String manaId,
                            @PathVariable("studentId")String stuId,
                            Scores score
    ) {
        
        if(studentsService.getById(stuId) == null) {
            return Response.error("学生账号不存在");
        }
        score.setStudentId(Integer.parseInt(stuId));
        if(scoresService.save(score)) {
            return Response.success("创建成功");
        } else {
            return Response.error("创建失败");
        }
    }

    /**
     * 通过id将学生从课程中删除
     * @param manaId
     * @param stuId
     * @param score
     * @return
     */
    @DeleteMapping("/{managerId}/score/{studentId}")
    public Response delScore(
                            @PathVariable("managerId")String manaId,
                            @PathVariable("studentId")String stuId,
                            Scores score
    ) {
        
        if(scoresService.removeById(stuId)) {
            return Response.success("删除成功");
        } else {
            return Response.error("删除失败");
        }
    }


    /**
     * 按照年级班级将学生添加到课程中
     * @param courId
     * @param grade
     * @param stuClass
     * @return
     */
    @PostMapping("/{managerId}/{courseId}/score/{grade}/{studentClass}")
    public Response createScores(
                            @PathVariable("courseId")String courId,
                            @PathVariable("grade")String grade,
                            @PathVariable("studentClass")String stuClass
    ) {
        if(coursesService.getById(courId) == null) {
            return Response.error("课程不存在");
        }
        List<Students> students;
        List<Scores> scores = new ArrayList<>();
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq("grade", grade);
        wrapper.eq("student_class", stuClass);
        students = studentsService.list(wrapper);
        if(students == null) {
            return Response.error("班级不存在");
        } else {
            Courses course = coursesService.getById(courId);
            for(Students student: students) {
                Scores score = new Scores();
                score.setCourseId(course.getCourseId());
                score.setStudentId(student.getStudentId());
                score.setTeacherId(course.getTeacherId());
                scores.add(score);
            }
            if(scoresService.saveBatch(scores)) {
                return Response.success("创建成功");
            }
        }
        return Response.error("创建失败");
    }

    /**
     * 通过年级班级将学生移除课程
     * @param courId
     * @param grade
     * @param stuClass
     * @return
     */
    @DeleteMapping("/{managerId}/{courseId}/score/{grade}/{studentClass}")
    public Response delScores(
                        @PathVariable("courseId")String courId,
                        @PathVariable("grade")String grade,
                        @PathVariable("studentClass")String stuClass
    ) {
        if(coursesService.getById(courId) == null) {
            return Response.error("课程不存在");
        }
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq("grade", grade);
        wrapper.eq("student_class", stuClass);
        List<Students> students = studentsService.list(wrapper);

        List<Integer> listId = students.stream().map(student -> student.getStudentId()).collect(Collectors.toList());
        if(scoresService.removeByIds(listId)) {
            return Response.success("删除成功");
        } else {
            return Response.error("删除失败");
        }
    }

    /**
     * 按班级修改成绩
     * @param courId
     * @param grade
     * @param stuClass
     * @param scores
     * @return
     */
    @PutMapping("/{managerId}/{courseId}/score/{grade}/{studentClass}")
    public Response updateScores(
                        @PathVariable("courseId")String courId,
                        @PathVariable("grade")String grade,
                        @PathVariable("studentClass")String stuClass,
                        List<Scores> scores
    ) {
        if(coursesService.getById(courId) == null) {
            return Response.error("课程不存在");
        }
        List<Students> students;
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        wrapper.eq("grade", grade);
        wrapper.eq("student_class", stuClass);
        students = studentsService.list(wrapper);
        if(students == null) {
            return Response.error("班级不存在");
        } else if(scoresService.updateBatchById(scores)) {
                return Response.success("修改成功");
        } else {
            return Response.error("修改失败");
        }
    }

    @GetMapping("{managerId}/{studnetId}/scores")
    public Response getScore(
                    @PathVariable("studentId")Integer stuId,
                    @RequestParam(value = "year", required = false)String year,
                    @RequestParam(value = "term", required = false)String term
    ) {
        List<StudentScores> list;
        if(term != null && year != null) {
            list = scoresService.getScoresStuById(stuId, year, term);
        } else if(year != null) {
            list = scoresService.getScoresStuById(stuId, year);
        } else {
            list = scoresService.getScoresStuById(stuId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("length", list.size());
        map.put("scores", list);
        return Response.success("获取成功", list);
    }

}
