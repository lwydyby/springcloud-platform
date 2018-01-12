package com.gameley.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.gameley.entity.Demo;




/**
 *
 *
 * @author WWMXD
 * @email 309980030@qq.com
 * @date 2018-01-12 10:59:07
 */

@TableName("demo")
public class Demo extends Model<Demo> {
	private static final long serialVersionUID=1L;
		    //
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

		    //
    @TableField("name")
    private String name;

		    //
    @TableField("createdate")
    private Date createdate;

		    //
    @TableField("remark")
    private String remark;

    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;


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
	public void setName(String name) {
			this.name = name;
			}
	/**
	 * 获取：
	 */
	public String getName() {
			return name;
			}
		/**
	 * 设置：
	 */
	public void setCreatedate(Date createdate) {
			this.createdate = createdate;
			}
	/**
	 * 获取：
	 */
	public Date getCreatedate() {
			return createdate;
			}
		/**
	 * 设置：
	 */
	public void setRemark(String remark) {
			this.remark = remark;
			}
	/**
	 * 获取：
	 */
	public String getRemark() {
			return remark;
			}
		/**
	 * 设置：
	 */
	public void setDeleteFlag(Integer deleteFlag) {
			this.deleteFlag = deleteFlag;
			}
	/**
	 * 获取：
	 */
	public Integer getDeleteFlag() {
			return deleteFlag;
			}
		@Override
	protected Serializable pkVal() {
        return this.id;
	}
}