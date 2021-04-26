package com.als.score.service;

import java.util.HashMap;
import java.util.Map;

import com.als.score.entity.Manager;
import com.als.score.entity.Students;
import com.als.score.entity.Teachers;
import com.als.score.mapper.ManagerMapper;
import com.als.score.mapper.StudentsMapper;
import com.als.score.mapper.TeachersMapper;
import com.als.score.utils.TokenGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    
    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private TeachersMapper teachersMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private TokenGenerator tokenGenerator;

    public Map<String, Object> validate(String userType, String account, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(userType.equals("学生")) {
            Students student = studentValidate(account, password);
            if(student == null) {
                return null;
            }
            String token = tokenGenerator.generate(student.getStudentId().toString());
            map.put("token", token);
            map.put("student", student);
        } else if(userType.equals("教师")) {
            Teachers teacher = teacherValidate(account, password);
            if(teacher == null) {
                return null;
            }
            String token = tokenGenerator.generate(teacher.getTeacherId().toString());
            map.put("token", token);
            map.put("teacher", teacher);
        } else if(userType.equals("教学管理员")) {
            Manager manager = managerValidate(account, password);
            if(manager == null) {
                return null;
            }
            String token = tokenGenerator.generate(manager.getManagerId().toString());
            map.put("token", token);
            map.put("manager", manager);
        } else {
            return null;
        }
        return map;
        
    }


    /**
     * 学生验证
     * 登录验证
     * @param account
     * @param password
     * @return 
     */
    public Students studentValidate(String account, String password) {
        Students student = studentsMapper.selectById(account);
        if(student != null) {
            if(student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    /**
     * 教师验证
     * @param account
     * @param password
     * @return
     */
    public Teachers teacherValidate(String account, String password) {
        Teachers teacher = teachersMapper.selectById(account);
        if(teacher != null) {
            if(teacher.getPassword().equals(password)) {
                return teacher;
            }
        }
        return null;
    }

    /**
     * 教师管理员验证
     * @param account
     * @param password
     * @return
     */
    public Manager managerValidate(String account, String password) {
        Manager manager = managerMapper.selectById(account);
        if(manager != null) {
            if(manager.getPassword().equals(password)) {
                return manager;
            }
        }
        return null;
    }
}