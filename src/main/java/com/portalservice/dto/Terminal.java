package com.portalservice.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.google.common.base.Function;
import com.portalservice.entity.Player;

public class Terminal {
	
	public Terminal(){
		
	}
	public Terminal(Player player){
		if(player != null){
			this.setId(player.getPlayerCode());
			this.setUsername(player.getUsername());
			this.setMacAddress(player.getMacAddress());
			this.setPassword(player.getPassword());
			this.setStatus(player.getActivityStatus());
			this.setLastChangePassword(player.getLastChangePassword());
			this.setTotalCredit(player.getTotalCredit());
			if(player.getLastLoading() != null){
				this.setLastLoadingAmount(player.getLastLoading().getAmount());
				this.setLastLoadingDate(player.getLastLoading().getTransactionDate());
			}
			if(player.getLastWithdrawal() != null){
				this.setLastWithDrawalAmount(player.getLastWithdrawal().getAmount());
				this.setLastWithdrawalDate(player.getLastWithdrawal().getTransactionDate());
			}
			this.setShopId(player.getAgent().getId());
			this.setShopCredit(player.getAgent().getCreditLimit());
			this.setSessionId(player.getSessionId());
		}
	}
	
	private String id;
	private String username;
	private String password;
	private String macAddress;
	private Character status;
	private Date lastChangePassword;
	private BigDecimal totalCredit;
	private BigDecimal lastWithDrawalAmount;
	private BigDecimal lastLoadingAmount;
	private BigDecimal shopCredit;
	private String registerIP;
	private int shopId;
	private Date lastLoadingDate;
	private Date lastWithdrawalDate;
	private String sessionId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public BigDecimal getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}	
	
	public BigDecimal getLastWithDrawalAmount() {
		return lastWithDrawalAmount;
	}
	public void setLastWithDrawalAmount(BigDecimal lastWithDrawalAmount) {
		this.lastWithDrawalAmount = lastWithDrawalAmount;
	}
	public BigDecimal getLastLoadingAmount() {
		return lastLoadingAmount;
	}
	public void setLastLoadingAmount(BigDecimal lastLoadingAmount) {
		this.lastLoadingAmount = lastLoadingAmount;
	}
	public Date getLastChangePassword() {
		return lastChangePassword;
	}
	public void setLastChangePassword(Date lastChangePassword) {
		this.lastChangePassword = lastChangePassword;
	}
	
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
	public String getRegisterIP() {
		return registerIP;
	}
	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}
		
	public Date getLastLoadingDate() {
		return lastLoadingDate;
	}
	public void setLastLoadingDate(Date lastLoadingDate) {
		this.lastLoadingDate = lastLoadingDate;
	}
	public Date getLastWithdrawalDate() {
		return lastWithdrawalDate;
	}
	
	public void setLastWithdrawalDate(Date lastWithdrawalDate) {
		this.lastWithdrawalDate = lastWithdrawalDate;
	}
	
	public BigDecimal getShopCredit() {
		return shopCredit;
	}
	public void setShopCredit(BigDecimal shopCredit) {
		this.shopCredit = shopCredit;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public static Function<Player, Terminal> fromPlayer() {
        return new Function<Player, Terminal>() {
                  	
        	@Override
            public Terminal apply(Player player) {
                return new Terminal(player);
            }
        };
    }
}
