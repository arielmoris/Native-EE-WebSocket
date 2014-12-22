package com.portalservice.dao;

import java.util.List;

public interface GenericDao<E> {
	public void save(E e);
	public E findById(long id);
	public List<E> findAll();
	public E findById(String id);
}
