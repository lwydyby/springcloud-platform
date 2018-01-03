package com.gameley.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.gameley.entity.Menu;
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
@TableName("base_menu")
public class Menu extends Model<Menu> {
	private static final long serialVersionUID=1L;
    //
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    //路径编码
    @TableField("code")
    private String code;

    //标题
    @TableField("title")
    private String title;

    //节点id
    @TableField("menu_id")
    private Integer menuId;

    //父级节点
    @TableField("parent_id")
    private Integer parentId;

    //资源路径
    @TableField("href")
    private String href;

    //图标
    @TableField("icon")
    private String icon;

    //
    @TableField("type")
    private String type;

    //排序
    @TableField("order_num")
    private Integer orderNum;

    //描述
    @TableField("description")
    private String description;

    //菜单上下级关系
    @TableField("path")
    private String path;

    //启用禁用
    @TableField("enabled")
    private String enabled;

    //
    @TableField("crt_time")
    private Date crtTime;

    //
    @TableField("crt_user")
    private String crtUser;

    //
    @TableField("crt_name")
    private String crtName;

    //
    @TableField("crt_host")
    private String crtHost;

    //
    @TableField("upd_time")
    private Date updTime;

    //
    @TableField("upd_user")
    private String updUser;

    //
    @TableField("upd_name")
    private String updName;

    //
    @TableField("upd_host")
    private String updHost;

    //
    @TableField("attr1")
    private String attr1;

    //
    @TableField("attr2")
    private String attr2;

    //
    @TableField("attr3")
    private String attr3;

    //
    @TableField("attr4")
    private String attr4;

    //
    @TableField("attr5")
    private String attr5;

    //
    @TableField("attr6")
    private String attr6;

    //
    @TableField("attr7")
    private String attr7;

    //
    @TableField("attr8")
    private String attr8;


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
	 * 设置：路径编码
	 */
	public void setCode(String code) {
			this.code = code;
			}
	/**
	 * 获取：路径编码
	 */
	public String getCode() {
			return code;
			}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
			this.title = title;
			}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
			return title;
			}
	/**
	 * 设置：节点id
	 */
	public void setMenuId(Integer menuId) {
			this.menuId = menuId;
			}
	/**
	 * 获取：节点id
	 */
	public Integer getMenuId() {
			return menuId;
			}
	/**
	 * 设置：父级节点
	 */
	public void setParentId(Integer parentId) {
			this.parentId = parentId;
			}
	/**
	 * 获取：父级节点
	 */
	public Integer getParentId() {
			return parentId;
			}
	/**
	 * 设置：资源路径
	 */
	public void setHref(String href) {
			this.href = href;
			}
	/**
	 * 获取：资源路径
	 */
	public String getHref() {
			return href;
			}
	/**
	 * 设置：图标
	 */
	public void setIcon(String icon) {
			this.icon = icon;
			}
	/**
	 * 获取：图标
	 */
	public String getIcon() {
			return icon;
			}
	/**
	 * 设置：
	 */
	public void setType(String type) {
			this.type = type;
			}
	/**
	 * 获取：
	 */
	public String getType() {
			return type;
			}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
			}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
			return orderNum;
			}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
			this.description = description;
			}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
			return description;
			}
	/**
	 * 设置：菜单上下级关系
	 */
	public void setPath(String path) {
			this.path = path;
			}
	/**
	 * 获取：菜单上下级关系
	 */
	public String getPath() {
			return path;
			}
	/**
	 * 设置：启用禁用
	 */
	public void setEnabled(String enabled) {
			this.enabled = enabled;
			}
	/**
	 * 获取：启用禁用
	 */
	public String getEnabled() {
			return enabled;
			}
	/**
	 * 设置：
	 */
	public void setCrtTime(Date crtTime) {
			this.crtTime = crtTime;
			}
	/**
	 * 获取：
	 */
	public Date getCrtTime() {
			return crtTime;
			}
	/**
	 * 设置：
	 */
	public void setCrtUser(String crtUser) {
			this.crtUser = crtUser;
			}
	/**
	 * 获取：
	 */
	public String getCrtUser() {
			return crtUser;
			}
	/**
	 * 设置：
	 */
	public void setCrtName(String crtName) {
			this.crtName = crtName;
			}
	/**
	 * 获取：
	 */
	public String getCrtName() {
			return crtName;
			}
	/**
	 * 设置：
	 */
	public void setCrtHost(String crtHost) {
			this.crtHost = crtHost;
			}
	/**
	 * 获取：
	 */
	public String getCrtHost() {
			return crtHost;
			}
	/**
	 * 设置：
	 */
	public void setUpdTime(Date updTime) {
			this.updTime = updTime;
			}
	/**
	 * 获取：
	 */
	public Date getUpdTime() {
			return updTime;
			}
	/**
	 * 设置：
	 */
	public void setUpdUser(String updUser) {
			this.updUser = updUser;
			}
	/**
	 * 获取：
	 */
	public String getUpdUser() {
			return updUser;
			}
	/**
	 * 设置：
	 */
	public void setUpdName(String updName) {
			this.updName = updName;
			}
	/**
	 * 获取：
	 */
	public String getUpdName() {
			return updName;
			}
	/**
	 * 设置：
	 */
	public void setUpdHost(String updHost) {
			this.updHost = updHost;
			}
	/**
	 * 获取：
	 */
	public String getUpdHost() {
			return updHost;
			}
	/**
	 * 设置：
	 */
	public void setAttr1(String attr1) {
			this.attr1 = attr1;
			}
	/**
	 * 获取：
	 */
	public String getAttr1() {
			return attr1;
			}
	/**
	 * 设置：
	 */
	public void setAttr2(String attr2) {
			this.attr2 = attr2;
			}
	/**
	 * 获取：
	 */
	public String getAttr2() {
			return attr2;
			}
	/**
	 * 设置：
	 */
	public void setAttr3(String attr3) {
			this.attr3 = attr3;
			}
	/**
	 * 获取：
	 */
	public String getAttr3() {
			return attr3;
			}
	/**
	 * 设置：
	 */
	public void setAttr4(String attr4) {
			this.attr4 = attr4;
			}
	/**
	 * 获取：
	 */
	public String getAttr4() {
			return attr4;
			}
	/**
	 * 设置：
	 */
	public void setAttr5(String attr5) {
			this.attr5 = attr5;
			}
	/**
	 * 获取：
	 */
	public String getAttr5() {
			return attr5;
			}
	/**
	 * 设置：
	 */
	public void setAttr6(String attr6) {
			this.attr6 = attr6;
			}
	/**
	 * 获取：
	 */
	public String getAttr6() {
			return attr6;
			}
	/**
	 * 设置：
	 */
	public void setAttr7(String attr7) {
			this.attr7 = attr7;
			}
	/**
	 * 获取：
	 */
	public String getAttr7() {
			return attr7;
			}
	/**
	 * 设置：
	 */
	public void setAttr8(String attr8) {
			this.attr8 = attr8;
			}
	/**
	 * 获取：
	 */
	public String getAttr8() {
			return attr8;
			}
	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}