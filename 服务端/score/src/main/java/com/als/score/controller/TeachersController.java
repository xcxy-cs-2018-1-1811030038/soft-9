package com.als.score.controller;


import com.als.score.entity.Response;
import com.als.score.entity.Teachers;
import com.als.score.service.impl.TeachersServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aion
 * @since 2021-03-10
 */
@RestController
@RequestMapping("/teachers")
public class TeachersController {

    @Autowired
    private TeachersServiceImpl teacherService;

    /**
     * 通过id获取教师信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response getTeacher(@PathVariable(value = "id", required = true)String id) {
        Teachers teacher = teacherService.getById(id);
        return Response.success("成功获取", teacher);
    }

    @RequestMapping("/error")
    public Response reErr() {
        return Response.error("教师账号无效");
    }
}
