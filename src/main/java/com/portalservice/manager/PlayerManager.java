package com.portalservice.manager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portalservice.dao.PlayerDao;
import com.portalservice.dto.ManagerResponse;
import com.portalservice.dto.Terminal;
import com.portalservice.entity.Player;

@Service(value="playerManager")
public class PlayerManager {
	
	PlayerDao playerDao;
	
	@Autowired
	public PlayerManager(PlayerDao playerDao){
		this.playerDao = playerDao;
	}
	
	@Transactional
	public ManagerResponse validatePlayerId(String playerId){
		
		ManagerResponse response = new ManagerResponse();
		Terminal terminal = null;
		Player player = playerDao.findbyPlayerCode(playerId);
		if(player != null){
			terminal = new Terminal(player);
		}
		
		response.setStatus(0);
		response.setMessage("Success");
		response.setObj(terminal);
		
		return response;
	}
}
