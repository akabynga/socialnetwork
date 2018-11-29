package com.socialnetwork.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.socialnetwork.dao.BaseEntityRepository;
import com.socialnetwork.entity.SocialNetworkEntity;

@Transactional
public abstract class DefaultService<I, E extends SocialNetworkEntity<I>> implements BaseEntityService<I, E> {

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<E> getAll(int amount, int skip) {
		return getRepository().findAll(amount, skip);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public E getById(I id) {
		return getRepository().findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean remove(I id) {
		return getRepository().delete(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public I update(E entity) {
		return getRepository().update(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public I create(E entity) {
		return getRepository().create(entity);
	}

	protected abstract BaseEntityRepository<I, E> getRepository();

}
