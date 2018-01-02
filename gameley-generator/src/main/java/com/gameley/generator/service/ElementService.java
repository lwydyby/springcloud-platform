package com.gameley.generator.service;


import com.baomidou.mybatisplus.service.IService;
import com.gameley.generator.entity.Element;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-02 16:21:36
 */
public interface ElementService extends  IService<Element> {
    public int deleteAll();
}
