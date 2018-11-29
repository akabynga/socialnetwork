package com.socialnetwork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.socialnetwork.entity.SocialNetworkEntity;

public abstract class BaseEntityRepository<I, E extends SocialNetworkEntity<I>> {

	@PersistenceContext
	private EntityManager entityManager;

	public List<E> findAll(int amount, int skip) {
		CriteriaQuery<E> criteria = createCriteriaBuilder();
		TypedQuery<E> q = getEntityManager().createQuery(criteria).setFirstResult(skip).setMaxResults(amount);
		return q.getResultList();
	}

	public I create(E entity) {
		getEntityManager().persist(entity);
		return entity.getId();
	}

	public E findById(I entityId) {
		E entity = getEntityManager().find(getClazz(), entityId);
		return entity;
	}

	public I update(E entity) {
		getEntityManager().merge(entity);
		return entity.getId();
	}

	public boolean delete(I entityId) {
		E e = getEntityManager().getReference(getClazz(), entityId);
		if (e != null && e.getId() != null) {
			return delete(e);
		}
		return false;
	}

	public boolean delete(E e) {
		assert e != null;

		getEntityManager().remove(e);
		return true;
	}

	protected CriteriaQuery<E> createCriteriaBuilder() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> criteria = builder.createQuery(getClazz());
		Root<E> root = criteria.from(getClazz());
		criteria.select(root);
		return criteria;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected abstract Class<E> getClazz();

}
