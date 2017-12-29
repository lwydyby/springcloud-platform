package com.gameley.service.impl;

import com.gameley.entity.BaseUser;
import com.gameley.dao.BaseUserDao;
import com.gameley.service.BaseUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lw
 * @since 2017-12-25
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserDao, BaseUser> implements BaseUserService {
	
}
