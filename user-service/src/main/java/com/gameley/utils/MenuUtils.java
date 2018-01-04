package com.gameley.utils;

import com.gameley.entity.Menu;
import com.gameley.vo.Model;
import com.gameley.vo.SubMenu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MenuUtils {
    public static List<Model> getmenu(List<Menu> menus){
        List<Menu> parentMenu=new ArrayList<>();
        List<Model> models=new ArrayList<>();
        for(Menu menu:menus){
            if(menu.getParentId()==-1){
                parentMenu.add(menu);
            }
        }
        parentMenu.sort(Comparator.comparing(Menu::getOrderNum));
        for(Menu menu:parentMenu){
            Model model=new Model();
            model.setName(menu.getTitle());
            model.setMenuid(String.valueOf(menu.getMenuId()));
            model.setUrl(menu.getPath());
            model.setSubMenu(findchildren(menu,menus));
            models.add(model);
        }
        return models;
    }
    public static List<SubMenu> findchildren(Menu m, List<Menu> menus){
        List<SubMenu> childmenu=new ArrayList<>();
        for(Menu menu:menus){
            if(m.getMenuId().equals(menu.getParentId())){
                SubMenu subMenu=new SubMenu();
                subMenu.setMenuid(String.valueOf(menu.getMenuId()));
                subMenu.setName(menu.getTitle());
                subMenu.setUrl(menu.getPath());
                subMenu.setSort(menu.getOrderNum());
                childmenu.add(subMenu);
            }
        }
        childmenu.sort(Comparator.comparing(SubMenu::getSort));
        return childmenu;
    }
}
