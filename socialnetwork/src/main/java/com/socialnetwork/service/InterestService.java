package com.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialnetwork.dao.BaseEntityRepository;
import com.socialnetwork.dao.InterestRepository;
import com.socialnetwork.entity.Interest;

@Service
@Transactional
public class InterestService extends DefaultService<Long, Interest> {

	@Autowired
	private InterestRepository interestRepository;

	@Override
	protected BaseEntityRepository<Long, Interest> getRepository() {
		return interestRepository;
	}
}
