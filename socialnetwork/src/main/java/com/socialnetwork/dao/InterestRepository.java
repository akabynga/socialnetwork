package com.socialnetwork.dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.socialnetwork.entity.Interest;

@Repository
public class InterestRepository extends BaseEntityRepository<Long, Interest> {

	@Override
	protected Class<Interest> getClazz() {
		return Interest.class;
	}

	public Interest findByName(String name) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Interest> criteria = builder.createQuery(getClazz());
		Root<Interest> root = criteria.from(getClazz());
		criteria.select(root);

		criteria.where(builder.and(builder.equal(root.get("name"), name)));

		TypedQuery<Interest> q = getEntityManager().createQuery(criteria);
		return q.getResultList().stream().findFirst().orElse(null);
	}
}
