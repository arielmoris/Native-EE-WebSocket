package com.portalservice.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.portalservice.dao.PlayerDao;
import com.portalservice.entity.Player;

@Repository
public class PlayerDaoImpl extends GenericDaoImpl<Player> implements PlayerDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	protected void init(){
		super.setEntityManager(em);
	}
	
	
	@Override
	public Player findbyPlayerCodeAndAgentId(String playerCode, int agentId) {
		Player player = null;
		try {
			String sql = "From Player player where player.playerCode = :playerCode and player.agent.id = :agent";
			Query query = em.createQuery(sql)
							.setParameter("playerCode", playerCode)
							.setParameter("agent", agentId);
			player = (Player) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public Player findbyPlayerCode(String playerCode) {
		Player player = null;
		try {
			String sql = "From Player where playerCode = :playerCode";
			Query query = em.createQuery(sql)
							.setParameter("playerCode", playerCode);
			player = (Player) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return player;
	}

}
