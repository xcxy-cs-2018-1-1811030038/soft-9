package com.als.score.service.impl;

import com.als.score.entity.Students;
import com.als.score.mapper.StudentsMapper;
import com.als.score.service.IStudentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aion
 * @since 2021-03-10
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements IStudentsService {

}
