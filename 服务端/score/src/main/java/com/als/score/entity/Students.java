package com.als.score.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author aion
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     * 自行set主键值
     */
    @TableId(value = "student_id", type = IdType.INPUT)
    private Integer studentId;

    private String studentName;

    private String grade;

    /**
     * 班级
     */
    private String studentClass;

    private String password;


}
