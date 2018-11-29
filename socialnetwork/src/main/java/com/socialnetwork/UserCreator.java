package com.socialnetwork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.socialnetwork.entity.Interest;
import com.socialnetwork.entity.User;

public class UserCreator {

	private Random random = new Random();
	private List<Interest> interest = new ArrayList<>();
	private int quantity = 10;

	{
		interest.add(new Interest("movies"));
		interest.add(new Interest("cars"));
		interest.add(new Interest("music"));
		interest.add(new Interest("hiking"));
		interest.add(new Interest("dancing"));
	}

	public UserCreator(int quantity) {
		super();
		this.quantity = quantity;
	}

	public List<User> getUsers() {
		List<User> peoples = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			String name = generateRandomString(6);
			User people = new User();
			int intNum = generateRandomInteger(1, 4, null);
			Set<Integer> nums = new HashSet<Integer>();
			for (int j = 0; j < intNum; j++) {
				int num = generateRandomInteger(0, 4, nums);
				people.getInterests().add(interest.get(num));
				nums.add(num);
			}
			people.setName(capitailizeWord(name));
			peoples.add(people);
		}
		return peoples;
	}

	private String generateRandomString(int length) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		StringBuilder buffer = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}

	private int generateRandomInteger(int min, int max, Set<Integer> exclude) {
		Integer val = null;
		int tries = max - min;
		do {
			val = random.nextInt((max - min) + 1) + min;
			tries--;
		} while (exclude != null && exclude.contains(val) || tries >= 0);
		return val;
	}

	private String capitailizeWord(String str) {
		StringBuffer s = new StringBuffer();

		char ch = ' ';
		for (int i = 0; i < str.length(); i++) {
			if (ch == ' ' && str.charAt(i) != ' ')
				s.append(Character.toUpperCase(str.charAt(i)));
			else
				s.append(str.charAt(i));
			ch = str.charAt(i);
		}
		return s.toString().trim();
	}
}
