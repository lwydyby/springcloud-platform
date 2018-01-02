package com.gameley.generator.service.impl;


import com.gameley.generator.mapper.ElementDao;
import com.gameley.generator.entity.Element;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gameley.generator.service.ElementService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-02 16:21:36
 */
@Service
public class ElementServiceImpl extends ServiceImpl<ElementDao, Element> implements ElementService {
	
}
