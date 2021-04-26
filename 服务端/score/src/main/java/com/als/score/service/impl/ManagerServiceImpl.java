package com.als.score.service.impl;

import com.als.score.entity.Manager;
import com.als.score.mapper.ManagerMapper;
import com.als.score.service.IManagerService;
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
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

}
