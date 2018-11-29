package com.socialnetwork.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.socialnetwork.entity.Interest;
import com.socialnetwork.entity.Pair;
import com.socialnetwork.entity.User;

public class IntersectionFinder {

	private List<User> users;

	public IntersectionFinder(List<User> users) {
		super();
		this.users = users;
	}

	public Map<Pair, Set<Interest>> findInterestIntersectionPairs() {
		List<User> used = new ArrayList<>();
		List<Pair> pairs = new ArrayList<>();

		Map<Pair, Set<Interest>> pairWeight = new HashMap<>();
		Map<Pair, Set<Interest>> result = new HashMap<>();
		for (int i = 0; i < users.size(); i++) {
			for (int j = i + 1; j < users.size(); j++) {
				pairs.add(new Pair(users.get(i), users.get(j)));
			}
			System.out.println(users.get(i));
		}

		for (int i = 0; i < pairs.size(); i++) {
			Pair p = pairs.get(i);
			Set<Interest> intersections = getIntersections(p.getFirst().getInterests(), p.getSecond().getInterests());
			if (intersections.size() > 0) {
				pairWeight.put(p, intersections);
			}
		}

		List<Pair> keysey = new ArrayList<>(pairWeight.keySet());
		int mSize = pairWeight.size();
		while (mSize > 0) {
			int max = 0;
			Pair maxIntersectionPair = null;
			for (int i = 0; i < pairWeight.size(); i++) {
				Pair p = keysey.get(i);
				if (used.contains(p.getFirst()) || used.contains(p.getSecond())) {
					continue;
				}
				Set<Interest> intersection = pairWeight.get(p);
				if (max < intersection.size()) {
					max = intersection.size();
					maxIntersectionPair = p;
				}
			}
			if (maxIntersectionPair != null) {
				result.put(maxIntersectionPair, pairWeight.get(maxIntersectionPair));
				used.add(maxIntersectionPair.getFirst());
				used.add(maxIntersectionPair.getSecond());
			}
			mSize--;
		}

		return result;
	}

	private static Set<Interest> getIntersections(List<Interest> i1, List<Interest> i2) {
		Set<Interest> checked = new HashSet<>();

		List<Interest> checkedList = null;
		List<Interest> varList = null;
		if (i1.size() > i2.size()) {
			checkedList = i1;
			varList = i2;
		} else {
			checkedList = i2;
			varList = i1;
		}

		for (int i = 0; i < varList.size(); i++) {
			if (!checked.contains(varList.get(i)) && checkedList.contains(varList.get(i))) {
				checked.add(varList.get(i));
			}
		}
		return checked;
	}

}
