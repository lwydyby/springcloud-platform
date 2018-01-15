package com.gameley.service.impl;

import com.gameley.entity.Menu;
import com.gameley.dao.MenuDao;
import com.gameley.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-03 14:39:38
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService  {

}
