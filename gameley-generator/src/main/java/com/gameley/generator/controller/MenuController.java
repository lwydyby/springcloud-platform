package com.gameley.generator.controller;


import com.gameley.generator.MenuGenerator;
import com.gameley.generator.service.ElementService;
import com.gameley.generator.service.MenuService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void generator(HttpServletResponse response) throws IOException {
        MenuGenerator menu=new MenuGenerator();
        byte[] data=menu.generator(menuService,elementService);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"webSetting.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());



    }
	
}
