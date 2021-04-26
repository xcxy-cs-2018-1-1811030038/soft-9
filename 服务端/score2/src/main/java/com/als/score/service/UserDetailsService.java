package com.als.score.service;

import com.als.score.entity.Manager;
import com.als.score.entity.Student;
import com.als.score.entity.Teacher;
import com.als.score.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IManagerService managerService;

    public User LoadUserByAccount(String account, String userType) {
        User user = new User();
        user.setAccount(account);
        user.setUserType(userType);

        // 从数据库中获取用户信息
        if(userType.equals("student")) {
            Student student = studentService.getById(account);
            if(student != null) {
                user.setUsername(student.getStudentName());
                user.setPassword(student.getPassword());
                user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_STUDENT"));
            } else {
                return null;
            }
        } else if(userType.equals("teacher")) {
            Teacher teacher = teacherService.getById(account);
            if(teacher != null) {
                user.setUsername(teacher.getTeacherName());
                user.setPassword(teacher.getPassword());
                user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_TEACHER"));
            } else {
                return null;
            }
        } else if(userType.equals("manager")) {
            Manager manager = managerService.getById(account);
            if(manager != null) {
                user.setUsername(manager.getManagerName());
                user.setPassword(manager.getPassword());
                user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_MANAGER"));
            } else {
                return null;
            }
        }

        return user;
    }
}
