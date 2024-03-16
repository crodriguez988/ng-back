package com.nextgame.mappers;

public interface Mapper <D, E>{
	public E mapToDto(D d);
	public D mapToEntity(E e);
}