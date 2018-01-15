package com.gameley.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.gameley.entity.Role;
//import lombok.Data;



/**
 * 
 *
 * @author WWMXD
 * @email 309980030@qq.com
 * @date 2018-01-03 14:39:38
 */
//@Data
@TableName("role")
public class Role extends Model<Role> {
	private static final long serialVersionUID=1L;
    //
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    //
    @TableField("role_name")
    private String roleName;

    //1 used, 0 not used
    @TableField("role_state")
    private Integer roleState;


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
	public void setRoleName(String roleName) {
			this.roleName = roleName;
			}
	/**
	 * 获取：
	 */
	public String getRoleName() {
			return roleName;
			}
	/**
	 * 设置：1 used, 0 not used
	 */
	public void setRoleState(Integer roleState) {
			this.roleState = roleState;
			}
	/**
	 * 获取：1 used, 0 not used
	 */
	public Integer getRoleState() {
			return roleState;
			}
	@Override
	protected Serializable pkVal() {
        return this.id;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", roleName='" + roleName + '\'' +
				", roleState=" + roleState +
				'}';
	}
}