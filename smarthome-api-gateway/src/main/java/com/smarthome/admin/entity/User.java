package com.smarthome.admin.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author liushihua
 * @since 2017-10-24
 */
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	@TableId(value="user_id", type= IdType.AUTO)
	private Integer userId;
	@TableField("user_name")
	private String userName;
	@TableField("user_account")
	private String userAccount;
	@TableField("user_password")
	private String userPassword;
	@TableField("is_valid")
	private String isValid;
	@TableField("organizer_id")
	private Integer organizerId;
	@TableField("is_admin")
	private String isAdmin;
	@TableField("mobile_number")
	private String mobileNumber;
	@TableField("phone_number")
	private String phoneNumber;
	
	@TableField(exist=false)
	private Set<Role> roleSet;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Integer getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(Integer organizerId) {
		this.organizerId = organizerId;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Set<Role> getRoleSet() {
		return roleSet;
	}
	
	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", userName=" + userName +
			", userAccount=" + userAccount +
			", userPassword=" + userPassword +
			", isValid=" + isValid +
			", organizerId=" + organizerId +
			", isAdmin=" + isAdmin +
			", mobileNumber=" + mobileNumber +
			", phoneNumber=" + phoneNumber +
//			", roleSet=" + roleSet.toArray() +
			"}";
	}
}
