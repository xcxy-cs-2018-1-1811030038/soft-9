package com.als.score.controller;


import com.als.score.entity.Courses;
import com.als.score.entity.Response;
import com.als.score.service.impl.CoursesServiceImpl;

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
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    private CoursesServiceImpl coursesService;


    /**
     * 通过课程id获取课程信息
     * @return
     */
    @GetMapping("/{id}")
    public Response getCourse(@PathVariable(value = "id", required = true)String id) {
        Courses course = coursesService.getById(id);
        return Response.success("获取成功", course);
    }

}
