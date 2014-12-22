package com.portalservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name="AGENTS")
public class Agent implements Serializable{
	@Id
	@Column(name="AgentID")
	private int id;
	private String brandName;
	private int parentAgentID;
	
	private String partnerId;
	private Character accountType;
	private Character status;
	private Date registerDate;
	private String lastLoginIp;
	private Date lastLoginDate;
	private BigDecimal creditLimit;
	private String agentLevel;
	private String maxLevel;
	private Double commissionRate;
	private Date lastChangePassword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getParentAgentID() {
		return parentAgentID;
	}
	public void setParentAgentID(int parentAgentID) {
		this.parentAgentID = parentAgentID;
	}
	public Character getAccountType() {
		return accountType;
	}
	public void setAccountType(Character accountType) {
		this.accountType = accountType;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creaditLimit) {
		this.creditLimit = creaditLimit;
	}
	public String getAgentLevel() {
		return agentLevel;
	}
	public void setAgentLevel(String agentLevel) {
		this.agentLevel = agentLevel;
	}
	public String getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
	}
	public Double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}
	public Date getLastChangePassword() {
		return lastChangePassword;
	}
	public void setLastChangePassword(Date lastChangePassword) {
		this.lastChangePassword = lastChangePassword;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
