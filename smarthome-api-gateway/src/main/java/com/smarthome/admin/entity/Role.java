package com.smarthome.admin.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Set;

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
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

	@TableId(value="role_id", type= IdType.AUTO)
	private Integer roleId;
	@TableField("role_name")
	private String roleName;
	@TableField("role_desc")
	private String roleDesc;
	@TableField("is_valid")
	private String isValid;
	@TableField("is_admin")
	private String isAdmin;
	@TableField("organizer_id")
	private BigDecimal organizerId;

	@TableField(exist=false)
	private Set<String> permissionSet;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public BigDecimal getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(BigDecimal organizerId) {
		this.organizerId = organizerId;
	}
	
	public Set<String> getPermissionSet() {
		return permissionSet;
	}
	
	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "Role{" +
			"roleId=" + roleId +
			", roleName=" + roleName +
			", roleDesc=" + roleDesc +
			", isValid=" + isValid +
			", isAdmin=" + isAdmin +
			", organizerId=" + organizerId +
			", permissionSet=" + permissionSet.toArray().toString() +
			"}";
	}
}
