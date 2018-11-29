package com.socialnetwork.dao;

import org.springframework.stereotype.Repository;

import com.socialnetwork.entity.User;

@Repository
public class UserRepository extends BaseEntityRepository<Long, User> {

	@Override
	protected Class<User> getClazz() {
		return User.class;
	}

}
