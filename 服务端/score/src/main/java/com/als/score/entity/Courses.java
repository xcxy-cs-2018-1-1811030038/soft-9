package com.als.score.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Courses implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.INPUT)
    private Integer courseId;

    private String courseName;

    private Integer TeacherId;
    
    private String year;

    private String term;
}
