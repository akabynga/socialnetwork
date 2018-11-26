package com.socialnetwork;

import java.util.Map;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialnetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialnetworkApplication.class, args);
		UserCreator userCreator = new UserCreator(10);
		IntersectionFinder finder = new IntersectionFinder(userCreator.getUsers());

		Map<Pair, Set<String>> result = finder.findInterestIntersectionPairs();

		System.out.println("Result: ");
		for (Pair p : result.keySet()) {
			System.out.println(p + ": " + result.get(p));
		}
	}

}
