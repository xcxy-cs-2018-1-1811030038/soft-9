package com.als.score.service;

import com.als.score.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aion
 * @since 2021-03-18
 */
public interface IManagerService extends IService<Manager> {

    /**
     * 获取管理员信息
     * @param managerId
     * @return
     */
    public Manager getManaget(String managerId);

    /**
     * 创建学生信息
     * @param students
     * @return
     */
    public boolean createStudents(List<Student> students);

    /**
     * 修改学生信息
     * @param students
     * @return
     */
    public boolean updateStudents(List<Student> students);

    /**
     * 创建教师信息
     * @param teachers
     * @return
     */
    public boolean createTeacehrs(List<Teacher> teachers);

    /**
     * 修改教师信息
     * @param teachers
     * @return
     */
    public boolean updateTeachers(List<Teacher> teachers);

    /**
     * 创建课程信息
     * @param courses
     * @return
     */
    public boolean createCourses(List<Course> courses);

    /**
     * 修改课程信息
     * @param courses
     * @return
     */
    public boolean updateCourses(List<Course> courses);

    /**
     * 创建选课信息
     * @param scores
     * @return
     */
    public boolean createScores(List<Score> scores);

    /**
     * 修改选课信息
     * @param scores
     * @return
     */
    public boolean updateScores(List<Score> scores);
}
