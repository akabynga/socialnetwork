package com.socialnetwork.service;

import java.util.List;

public interface BaseEntityService<I, E> {

	List<E> getAll(int amount, int skip);

	E getById(I id);

	boolean remove(I id);

	I update(E entity);

	I create(E entity);
}
