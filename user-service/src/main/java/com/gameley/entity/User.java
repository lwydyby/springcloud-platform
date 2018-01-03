package com.gameley.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.gameley.entity.User;
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
@TableName("user")
public class User extends Model<User> {
	private static final long serialVersionUID=1L;
    //
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    //用户名
    @TableField("username")
    private String username;

    //权限id（显示菜单）
    @TableField("roleid")
    private Integer roleid;

    //密码
    @TableField("password")
    private Integer password;


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
	 * 设置：用户名
	 */
	public void setUsername(String username) {
			this.username = username;
			}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
			return username;
			}
	/**
	 * 设置：权限id（显示菜单）
	 */
	public void setRoleid(Integer roleid) {
			this.roleid = roleid;
			}
	/**
	 * 获取：权限id（显示菜单）
	 */
	public Integer getRoleid() {
			return roleid;
			}
	/**
	 * 设置：密码
	 */
	public void setPassword(Integer password) {
			this.password = password;
			}
	/**
	 * 获取：密码
	 */
	public Integer getPassword() {
			return password;
			}
	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}