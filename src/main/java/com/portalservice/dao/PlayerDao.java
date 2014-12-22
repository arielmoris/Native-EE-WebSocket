package com.portalservice.dao;

import com.portalservice.entity.Player;

public interface PlayerDao extends GenericDao<Player>{
	public Player findbyPlayerCode(String playerCode);
	public Player findbyPlayerCodeAndAgentId(String playerCode, int agentId);
}
