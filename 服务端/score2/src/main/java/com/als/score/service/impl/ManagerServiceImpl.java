package com.als.score.service.impl;

import com.als.score.entity.*;
import com.als.score.mapper.*;
import com.als.score.service.IManagerService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager getManaget(String managerId) {
        return managerMapper.selectById(managerId);
    }

    @Override
    @Transactional
    public boolean createStudents(List<Student> students) {
        try {
            for (Student student: students) {
                studentMapper.insert(student);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStudents(List<Student> students) {

        try {
            for (Student student: students) {
                studentMapper.update(student, new UpdateWrapper<Student>().eq("studnet_id", student.getStudentId())
                        .set("student_name", student.getStudentName())
                        .set("grade", student.getGrade())
                        .set("student_class", student.getStudentClass())
                        .set("password", student.getPassword()));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean createTeacehrs(List<Teacher> teachers) {
        try {
            for (Teacher teacher: teachers) {
                teacherMapper.insert(teacher);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTeachers(List<Teacher> teachers) {
        try {
            for (Teacher teacher: teachers) {
                teacherMapper.update(teacher, new UpdateWrapper<Teacher>().eq("teacher_id", teacher.getTeacherId())
                        .set("teacher_name", teacher.getTeacherName())
                        .set("password", teacher.getPassword()));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean createCourses(List<Course> courses) {
        try {
            for (Course course: courses) {
                courseMapper.insert(course);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCourses(List<Course> courses) {
        try {
            for (Course course: courses) {
                courseMapper.update(course, new UpdateWrapper<Course>().eq("course_id", course.getCourseId())
                        .set("course_name", course.getCourseName())
                        .set("teacher_id", course.getTeacherId())
                        .set("term", course.getTerm())
                        .set("year", course.getYear()));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean createScores(List<Score> scores) {
        try {
            for (Score score: scores) {
                scoreMapper.insert(score);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateScores(List<Score> scores) {
        try {
            for (Score score: scores) {
                scoreMapper.update(score, new UpdateWrapper<Score>().eq("course_id", score.getScoreId())
                        .set("student_id", score.getStudentId())
                        .set("course_id", score.getCourseId()));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
