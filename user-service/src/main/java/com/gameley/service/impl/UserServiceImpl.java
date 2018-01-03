package com.gameley.service.impl;

import com.gameley.entity.User;
import com.gameley.dao.UserDao;
import com.gameley.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-03 14:39:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService  {
	
}
