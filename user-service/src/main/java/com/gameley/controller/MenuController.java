package com.gameley.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.gameley.common.context.BaseContextHandler;
import com.gameley.entity.Menu;
import com.gameley.entity.Role;
import com.gameley.entity.RoleMenu;
import com.gameley.entity.User;
import com.gameley.service.MenuService;
import com.gameley.service.RoleMenuService;
import com.gameley.service.RoleService;
import com.gameley.service.UserService;
import com.gameley.vo.Model;
import com.gameley.vo.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MenuController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping("getallmenu")
	public List<Model> getAllMenu(){
        String id=BaseContextHandler.getUserID();
        User user =userService.selectById(id);
        Integer roleid=user.getRoleid();
        List<RoleMenu> rolemenus=roleMenuService.selectList(Condition.create().eq("role_id",roleid));
        List<Menu> menus=new ArrayList<>();
        for(RoleMenu roleMenu:rolemenus){
            menus.add(menuService.selectOne(Condition.create().eq("menu_id",roleMenu.getMenuId())));

        }
        return getmenu(menus);


    }

    public List<Model> getmenu(List<Menu> menus){
        List<Menu> parentMenu=new ArrayList<>();
        List<Model> models=new ArrayList<>();
        for(Menu menu:menus){
            if(menu.getParentId()==-1){
                parentMenu.add(menu);
            }
        }
        for(Menu menu:parentMenu){
            Model model=new Model();
            model.setName(menu.getTitle());
            model.setMenuid(String.valueOf(menu.getMenuId()));
            model.setUrl(menu.getHref());
            model.setList(findchildren(menu,menus));
            models.add(model);
        }
        return models;
    }
    public List<SubMenu> findchildren(Menu m,List<Menu> menus){
        List<SubMenu> childmenu=new ArrayList<>();
        for(Menu menu:menus){
            if(m.getMenuId().equals(menu.getParentId())){
                SubMenu subMenu=new SubMenu();
                subMenu.setMenuid(String.valueOf(menu.getMenuId()));
                subMenu.setName(menu.getTitle());
                subMenu.setUrl(menu.getHref());
                childmenu.add(subMenu);
            }
        }
        return childmenu;
    }

}
