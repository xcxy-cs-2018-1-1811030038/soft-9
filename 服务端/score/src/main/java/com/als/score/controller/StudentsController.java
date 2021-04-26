package com.als.score.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.als.score.entity.Response;
import com.als.score.entity.StudentScores;
import com.als.score.entity.Students;
import com.als.score.service.impl.ScoresServiceImpl;
import com.als.score.service.impl.StudentsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/student")
public class StudentsController {

    @Autowired
    private ScoresServiceImpl scoresService;
    @Autowired
    private StudentsServiceImpl studentsService;

    /**
     * 通过学生id获取学生成绩
     * @param id    学号 必选
     * @param year  年份    可选
     * @param term  学期    可选
     * @return
     */
    @GetMapping("/{id}/scores")
    public Response getScores(
                        @PathVariable("id")Integer id,
                        @RequestParam(value = "year", required = false)String year,
                        @RequestParam(value = "term", required = false)String term
    ) {
        log.debug("id:{}", id);
        log.debug("year:{}", year);
        log.debug("term:{}", term);

        List<StudentScores> list;
        if(term != null && year != null) {
            list = scoresService.getScoresStuById(id, year, term);
        } else if(year != null) {
            list = scoresService.getScoresStuById(id, year);
        } else {
            list = scoresService.getScoresStuById(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("length", list.size());
        map.put("scores", list);
        return Response.success("获取成功", list);
    }

    /**
     * 通过id获取学生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Response getStudent(@PathVariable(value = "id", required = true)String id) {
        Students student = studentsService.getById(id);
        return Response.success("获取成功", student);
    }
}
