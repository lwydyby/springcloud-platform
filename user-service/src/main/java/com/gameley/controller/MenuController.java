package com.gameley.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.gameley.common.context.BaseContextHandler;
import com.gameley.common.msg.BaseResponse;
import com.gameley.common.msg.ObjectRestResponse;
import com.gameley.entity.Menu;

import com.gameley.entity.RoleMenu;
import com.gameley.entity.User;

import com.gameley.service.MenuService;
import com.gameley.service.RoleMenuService;
import com.gameley.service.RoleService;
import com.gameley.service.UserService;
import com.gameley.utils.JwtHelper;
import com.gameley.utils.MenuUtils;
import com.gameley.vo.Audience;
import com.gameley.vo.Model;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-03 14:39:38
 */

@RestController
@RequestMapping("menu")
@EnableConfigurationProperties(Audience.class)
public class MenuController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private Audience audienceEntity;
    @ApiOperation(value = "获取菜单信息" ,httpMethod ="POST")
    @RequestMapping("getallmenu")
	public BaseResponse getAllMenu(){
        Integer id= Integer.valueOf(BaseContextHandler.getUserID());
        User user =userService.selectById(id);
        Integer roleid=user.getRoleid();
        List<RoleMenu> rolemenus=roleMenuService.selectList(Condition.create().eq("role_id",roleid));
        List<Menu> menus=new ArrayList<>();
        for(RoleMenu roleMenu:rolemenus){
            Menu menu=menuService.selectOne(Condition.create().eq("menu_id",roleMenu.getMenuId()));
            if(menu!=null){
                menus.add(menu);
            }


        }
        List<RoleMenu> roleMenuList=roleMenuService.selectList(Condition.create().eq("role_id",roleid));
        List<Integer> list=new ArrayList<>();
        for(RoleMenu roleMenu:roleMenuList){
            if(roleMenu.getMenuId()>1000){
                list.add(roleMenu.getMenuId());
            }

        }
        List<Model> models=MenuUtils.getmenu(menus);
        String accessToken = JwtHelper.createJWT(models,list
                , audienceEntity.getClientId(), audienceEntity.getName(),
                audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        objectRestResponse.setData(accessToken);

        return objectRestResponse;


    }



}
