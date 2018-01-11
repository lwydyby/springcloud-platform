package com.gameley.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gameley.common.constant.RestCodeConstants;
import com.gameley.common.msg.BaseResponse;
import com.gameley.common.msg.ListRestResponse;
import com.gameley.common.msg.ObjectRestResponse;
import com.gameley.entity.Element;
import com.gameley.entity.Menu;
import com.gameley.entity.Role;
import com.gameley.entity.RoleMenu;
import com.gameley.service.ElementService;
import com.gameley.service.MenuService;
import com.gameley.service.RoleMenuService;
import com.gameley.service.RoleService;
import com.gameley.utils.MenuUtils;
import com.gameley.utils.TreeUtils;
import com.gameley.vo.Model;
import com.gameley.vo.SubMenu;
import com.gameley.vo.Tree;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("role")
public class RoleController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ElementService elementService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleService roleService;
    @ApiOperation(value = "获取菜单树" ,httpMethod ="POST")
    @RequestMapping("getMenuTree")
    private List<Tree> getTree(){
        List<Menu> menus=menuService.selectList(Condition.EMPTY);
        List<Model> models=MenuUtils.getmenu(menus);
        List<Tree> trees=new ArrayList<>();
        for(Model model:models){
            Tree tree=new Tree();
            tree.setId(model.getMenuid());
            tree.setLabel(model.getName());
            List<SubMenu> subMenus=model.getSubMenu();
            List<Tree> tre=new ArrayList<>();
            for(SubMenu subMenu:subMenus){
                Tree t1=new Tree();
                t1.setLabel(subMenu.getName());
                t1.setId(subMenu.getMenuid());
                String menuid=subMenu.getMenuid();
                List<Element> elements=elementService.selectList(Condition.create().eq("menu_id",menuid));
                t1.setChildren(TreeUtils.findchildren(elements));
                tre.add(t1);
            }
            tree.setChildren(tre);
            trees.add(tree);

        }
        return trees;

    }
    @ApiOperation(value = "获取权限组信息" ,httpMethod ="POST")
    @RequestMapping("getRoleName")
	public ObjectRestResponse getRoleName(){


        List<Role> roles=roleService.selectList(Condition.EMPTY);
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        objectRestResponse.setData(roles);
        objectRestResponse.setRel(true);
        return objectRestResponse;
    }
    @ApiOperation(value = "根据id查询权限组" ,httpMethod ="POST")
    @RequestMapping("getRoleById")
    private ObjectRestResponse getRoleById(@RequestBody JSONObject result){
        String roleid= result.getString("roleid");
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        List<RoleMenu> roleMenus=roleMenuService.selectList(Condition.create().eq("role_id",roleid));
        List<String> menuid=roleMenus.stream().map((RoleMenu roleMenu) -> roleMenu.getMenuId().toString()).collect(Collectors.toList());
        objectRestResponse.setData(menuid);
        return objectRestResponse;
    }
    @ApiOperation(value = "新建权限组" ,httpMethod ="POST")
    @RequestMapping("addRole")
    private ObjectRestResponse addRole(@RequestBody JSONObject result){
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        Role role=new Role();
        role.setRoleName(result.getString("rolename"));
        role.setRoleState(result.getInteger("rolestate"));
        Boolean flag=roleService.insert(role);
        if(flag){
            objectRestResponse.setData(role);
            objectRestResponse.setRel(true);
        }else {
            objectRestResponse.setRel(false);
        }
        return objectRestResponse;
    }
    @ApiOperation(value = "编辑权限组" ,httpMethod ="POST")
    @RequestMapping("editRole")
    private BaseResponse editRole(@RequestBody Role role){
        Boolean flag=roleService.updateById(role);
        if(flag){
            return new BaseResponse(200,"编辑成功");
        }else{
            return new BaseResponse(RestCodeConstants.EDIT_ERROR_CODE,"编辑失败");
        }

    }
    @ApiOperation(value = "保存权限设置" ,httpMethod ="POST")
    @RequestMapping("saveRolebyId")
    private BaseResponse saveRoleById(@RequestBody JSONObject jsonObject){
        Integer roleid=jsonObject.getInteger("roleid");
        List<Integer> menuids= jsonObject.getJSONArray("menuids").toJavaList(Integer.class);
        roleMenuService.delete(Condition.create().eq("role_id",roleid));
        List<RoleMenu> roleMenus=menuids.stream().map(integer -> {
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setRoleId(roleid);
            roleMenu.setMenuId(integer);
            return roleMenu;
        }).collect(Collectors.toList());
        Boolean flag=roleMenuService.insertBatch(roleMenus);
        if(flag){
            return new BaseResponse(200,"编辑成功");
        }else{
            return new BaseResponse(RestCodeConstants.EDIT_ERROR_CODE,"编辑失败");
        }
    }


}
