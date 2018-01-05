package com.gameley.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.gameley.common.context.BaseContextHandler;
import com.gameley.common.msg.BaseResponse;
import com.gameley.common.msg.ObjectRestResponse;
import com.gameley.entity.RoleMenu;
import com.gameley.entity.User;
import com.gameley.gameleyauth.annotation.IgnoreUserToken;
import com.gameley.service.ElementService;
import com.gameley.service.MenuService;
import com.gameley.service.RoleMenuService;
import com.gameley.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-03 14:39:38
 */

@RestController
@RequestMapping("element")
public class ElementController {
    @Autowired
    ElementService elementService;
    @Autowired
    UserService userService;
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    MenuService menuService;
    @ApiOperation(value = "获取按钮权限" ,httpMethod ="POST")
    @RequestMapping("getElementCode")
    private BaseResponse getElementCode(){
        Integer userid= Integer.valueOf(BaseContextHandler.getUserID());
        User user=userService.selectById(userid);
        Integer roleid=user.getRoleid();
        List<RoleMenu> roleMenuList=roleMenuService.selectList(Condition.create().eq("role_id",roleid));
        List<Integer> list=new ArrayList<>();
        for(RoleMenu roleMenu:roleMenuList){
            if(roleMenu.getMenuId()>1000){
                list.add(roleMenu.getMenuId());
            }

        }
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }
    @ApiOperation(value = "获取权限（暂未使用）" ,httpMethod ="POST")
    @RequestMapping("permission")
    @IgnoreUserToken
    private Integer getpermission(String uri){
        int count=elementService.selectCount(Condition.create().like("uri",uri));
        int counts=menuService.selectCount(Condition.create().like("href",uri));
        if(count+counts==0){
            return 0;
        }else{
            return 1;
        }


    }
	
}
