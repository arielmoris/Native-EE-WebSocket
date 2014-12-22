package com.portalservice.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@MappedSuperclass
public class CreditTransaction {
	
	@Id
	@Column(name = "ReferenceID")
	private int refId;
	
	@Column(name = "TranDateTime")
	private Date transactionDate;
	
	@Column(name = "Amount")
	private BigDecimal amount;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "PlayerCode")
	private Player player;
	
	private Character accountType;
	private String partnerId;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "AgentID")
	private Agent agent;
	
	private String tranIp;
	private String approvedBy;
	private Date approvedDate;
	private BigDecimal endingBalance;
	
	public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Character getAccountType() {
		return accountType;
	}

	public void setAccountType(Character accountType) {
		this.accountType = accountType;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getTranIp() {
		return tranIp;
	}

	public void setTranIp(String tranIp) {
		this.tranIp = tranIp;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public BigDecimal getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(BigDecimal endingBalance) {
		this.endingBalance = endingBalance;
	}
	
	
}
