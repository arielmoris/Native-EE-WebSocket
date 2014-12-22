package com.portalservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.gson.Gson;

@Entity
@Table(name = "Players")
public class Player {
	
	@Id
	@Column(name = "PlayerCode")
	private String playerCode;
	private String playerLevel;
	private String partnerID;
	
	@ManyToOne
	@JoinColumn(name="AgentID")
	private Agent agent;
	
	private String username;
	private String password;
	private Character accountType;
	private int playerCategory;
	private String playerName;
	private String mobileNo;
	private String email;
	private String bankName;
	private String accountNo;
	private Date registerDate;
	private String registerIp;
	private String siteUrl;
	private Character status;
	private Character activityStatus;
	private BigDecimal totalCredit;
	private Date lastLogin;
	private String lastLoginIp;
	private Date lastChangePassword;
	private String macAddress;
	private String sessionId;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LastLoadingID")
	private Loading lastLoading;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LastWithdrawalID")
	private Withdrawal lastWithdrawal;

	public String getPlayerCode() {
		return playerCode;
	}

	public void setPlayerCode(String playerCode) {
		this.playerCode = playerCode;
	}

	public String getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(String playerLevel) {
		this.playerLevel = playerLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getAccountType() {
		return accountType;
	}

	public void setAccountType(Character accountType) {
		this.accountType = accountType;
	}

	public int getPlayerCategory() {
		return playerCategory;
	}

	public void setPlayerCategory(int playerCategory) {
		this.playerCategory = playerCategory;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Character getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Character activityStatus) {
		this.activityStatus = activityStatus;
	}

	public BigDecimal getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastChangePassword() {
		return lastChangePassword;
	}

	public void setLastChangePassword(Date lastChangePassword) {
		this.lastChangePassword = lastChangePassword;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Loading getLastLoading() {
		return lastLoading;
	}

	public void setLastLoading(Loading lastLoading) {
		this.lastLoading = lastLoading;
	}

	public Withdrawal getLastWithdrawal() {
		return lastWithdrawal;
	}

	public void setLastWithdrawal(Withdrawal lastWithdrawal) {
		this.lastWithdrawal = lastWithdrawal;
	}
	
	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
