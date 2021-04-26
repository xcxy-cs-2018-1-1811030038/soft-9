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
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "manager_id", type = IdType.INPUT)
    private String managerId;

    private String managerName;

    private String password;


}
