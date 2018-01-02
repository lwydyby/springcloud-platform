package com.gameley.generator.controller;


import com.gameley.generator.MenuGenerator;
import com.gameley.generator.service.ElementService;
import com.gameley.generator.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-02 16:21:35
 */

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private ElementService elementService;

    @RequestMapping(value = "generator",method = RequestMethod.GET)
    public String generator(){
        MenuGenerator menu=new MenuGenerator();
        menu.generator(menuService,elementService);
        return "写入成功";


    }
	
}
