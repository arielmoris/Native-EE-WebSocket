package com.portalservice.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.portalservice.dao.GenericDao;

public class GenericDaoImpl<E> implements GenericDao<E>{
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<E> clazz;
	
	@SuppressWarnings(value="unchecked")
	public GenericDaoImpl(){
		ParameterizedType t = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<E>) t.getActualTypeArguments()[0];
	}
	
	@Transactional
	@Override
	public void save(E e) {
		em.merge(e);
	}
	
	@Override
	public E findById(long id) {
		return em.find(clazz, new Long(id).intValue());
	}

	@Override
	public E findById(String id) {
		return em.find(clazz, id);
	}
	
	@Override
	public List<E> findAll() {
		List<E> list = new ArrayList<E>();
		String hql = "From "+clazz.getSimpleName();
		Query query = em.createQuery(hql);
		list = query.getResultList();
		return list;
	}
	
	protected void setEntityManager(EntityManager em){
		this.em = em;
	}

}
