package com.als.score.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
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
public class Scores implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer courseId;

    private Integer teacherId;

    private Integer studentId;

    private Integer socre;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
