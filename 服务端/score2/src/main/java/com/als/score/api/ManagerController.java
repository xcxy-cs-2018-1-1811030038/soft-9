package com.als.score.api;

import com.als.score.entity.*;
import com.als.score.service.ICourseService;
import com.als.score.service.IManagerService;
import com.als.score.service.IStudentService;
import com.als.score.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/manager")
@RestController
public class ManagerController implements ManagerApi{

    @Autowired
    private IManagerService managerService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private ICourseService courseService;

    @Override
    public ResponseEntity<Manager> getManager(String managerId) {
        Manager manager = managerService.getManaget(managerId);

        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createStudents(List<Student> students) {
        log.debug("studnets:{}", students);
        if (managerService.createStudents(students)) {
            return new ResponseEntity<>("创建成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("创建失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> createTeachers(List<Teacher> teachers) {
        if (managerService.createTeacehrs(teachers)) {
            return new ResponseEntity<>("创建成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("创建失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> createCourses(List<Course> courses) {
        if (managerService.createCourses(courses)) {
            return new ResponseEntity<>("创建成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("创建失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> createScore(List<Score> scores) {
        if (managerService.createScores(scores)) {
            return new ResponseEntity<>("创建成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("创建失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> updateStudents(List<Student> students) {
        if (managerService.updateStudents(students)) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> updateTeachers(List<Teacher> teachers) {
        if (managerService.updateTeachers(teachers)) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> updateCourses(List<Course> courses) {
        if (managerService.updateCourses(courses)) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> updateScore(List<Score> scores) {
        if (managerService.updateScores(scores)) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> delStudent(String studentId) {
        if (studentService.removeById(studentId)) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("删除失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<String> delTeacher(String teacherId) {
        if (teacherService.removeById(teacherId)) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("删除失败", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<Course> getCourse(String courseId) {
        Course course = courseService.getById(courseId);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
        }
    }
}
