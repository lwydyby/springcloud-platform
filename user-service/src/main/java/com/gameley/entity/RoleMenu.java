package com.gameley.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.gameley.entity.RoleMenu;
import com.baomidou.mybatisplus.activerecord.Model;
//import lombok.Data;



/**
 * 
 *
 * @author WWMXD
 * @email 309980030@qq.com
 * @date 2018-01-03 14:39:38
 */
//@Data
@TableName("role_menu")
public class RoleMenu extends Model<RoleMenu> {
	private static final long serialVersionUID=1L;
    //
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    //
    @TableField("role_id")
    private Integer roleId;

    //
    @TableField("menu_id")
    public Integer menuId;


	/**
	 * 设置：
	 */
	public void setId(Integer id) {
			this.id = id;
			}
	/**
	 * 获取：
	 */
	public Integer getId() {
			return id;
			}
	/**
	 * 设置：
	 */
	public void setRoleId(Integer roleId) {
			this.roleId = roleId;
			}
	/**
	 * 获取：
	 */
	public Integer getRoleId() {
			return roleId;
			}
	/**
	 * 设置：
	 */
	public void setMenuId(Integer menuId) {
			this.menuId = menuId;
			}
	/**
	 * 获取：
	 */
	public Integer getMenuId() {
			return menuId;
			}
	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}