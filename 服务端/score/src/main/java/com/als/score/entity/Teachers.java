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
public class Teachers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "teacher_id", type = IdType.INPUT)
    private Integer teacherId;

    private String teacherName;

    private String password;


}
