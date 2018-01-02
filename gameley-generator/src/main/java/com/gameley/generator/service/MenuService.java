package com.gameley.generator.service;


import com.baomidou.mybatisplus.service.IService;
import com.gameley.generator.entity.Menu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-02 16:21:35
 */
public interface MenuService extends  IService<Menu> {
	public int deleteAll();
}
