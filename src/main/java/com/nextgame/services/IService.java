package com.nextgame.services;

import java.util.List;

public interface IService <E, ID>{
	public List<E> getAll();
	
	public E getById(ID id);
	
	public E save (E entity);
	
	public E update (E entity);
	
	public void delete (ID id);
}