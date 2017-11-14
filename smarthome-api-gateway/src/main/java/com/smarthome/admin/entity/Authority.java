package com.smarthome.admin.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
@TableName("sys_authority")
public class Authority extends Model<Authority> {

    private static final long serialVersionUID = 1L;

	@TableId(value="authority_id", type= IdType.AUTO)
	private Integer authorityId;
	@TableField("parent_authority_id")
	private BigDecimal parentAuthorityId;
	@TableField("authority_name")
	private String authorityName;
	@TableField("authority_type")
	private BigDecimal authorityType;
	@TableField("authority_url")
	private String authorityUrl;
	@TableField("authority_flag")
	private String authorityFlag;
	@TableField("authority_level")
	private BigDecimal authorityLevel;
	@TableField("disp_order")
	private BigDecimal dispOrder;
	@TableField("is_valid")
	private String isValid;
	@TableField("is_show")
	private String isShow;
	@TableField("permission")
	private String permission;


	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public BigDecimal getParentAuthorityId() {
		return parentAuthorityId;
	}

	public void setParentAuthorityId(BigDecimal parentAuthorityId) {
		this.parentAuthorityId = parentAuthorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public BigDecimal getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(BigDecimal authorityType) {
		this.authorityType = authorityType;
	}

	public String getAuthorityUrl() {
		return authorityUrl;
	}

	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}

	public String getAuthorityFlag() {
		return authorityFlag;
	}

	public void setAuthorityFlag(String authorityFlag) {
		this.authorityFlag = authorityFlag;
	}

	public BigDecimal getAuthorityLevel() {
		return authorityLevel;
	}

	public void setAuthorityLevel(BigDecimal authorityLevel) {
		this.authorityLevel = authorityLevel;
	}

	public BigDecimal getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(BigDecimal dispOrder) {
		this.dispOrder = dispOrder;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	protected Serializable pkVal() {
		return this.authorityId;
	}

	@Override
	public String toString() {
		return "Authority{" +
			"authorityId=" + authorityId +
			", parentAuthorityId=" + parentAuthorityId +
			", authorityName=" + authorityName +
			", authorityType=" + authorityType +
			", authorityUrl=" + authorityUrl +
			", authorityFlag=" + authorityFlag +
			", authorityLevel=" + authorityLevel +
			", dispOrder=" + dispOrder +
			", isValid=" + isValid +
			", isShow=" + isShow +
			"}";
	}
}
