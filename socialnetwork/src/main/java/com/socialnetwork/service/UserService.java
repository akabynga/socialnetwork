package com.socialnetwork.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialnetwork.dao.BaseEntityRepository;
import com.socialnetwork.dao.InterestRepository;
import com.socialnetwork.dao.UserRepository;
import com.socialnetwork.entity.Interest;
import com.socialnetwork.entity.Pair;
import com.socialnetwork.entity.User;
import com.socialnetwork.logic.IntersectionFinder;
import com.socialnetwork.logic.UserCreator;

@Service
@Transactional
public class UserService extends DefaultService<Long, User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterestRepository interestRepository;

	@Override
	protected BaseEntityRepository<Long, User> getRepository() {
		return userRepository;
	}

	@Override
	public Long create(User entity) {
		List<Interest> interests = new ArrayList<Interest>();

		for (Interest i : entity.getInterests()) {
			Interest interest = interestRepository.findByName(i.getName());
			if (interest != null) {
				interests.add(interest);
			} else {
				interests.add(i);
			}
		}
		entity.setInterests(interests);
		return super.create(entity);
	}

	public void generateUsers(Long quantity) {
		UserCreator userCreator = new UserCreator(10);
		for (User user : userCreator.getUsers()) {
			create(user);
		}
	}

	public Map<Pair, Set<Interest>> getPairIntersectionByInterests() {
		List<User> users = userRepository.findAll(null, null);
		IntersectionFinder finder = new IntersectionFinder(users);
		return finder.findInterestIntersectionPairs();
	}

}
