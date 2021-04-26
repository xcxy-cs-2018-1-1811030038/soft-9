package com.als.score.service.impl;

import com.als.score.entity.Courses;
import com.als.score.mapper.CoursesMapper;
import com.als.score.service.ICoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses> implements ICoursesService {
    
}
