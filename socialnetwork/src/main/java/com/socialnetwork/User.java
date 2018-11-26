package com.socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<String> interests;

	public User() {
		super();
	}

	public User(String name, List<String> interests) {
		super();
		this.name = name;
		this.interests = interests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getInterests() {
		if (interests == null) {
			interests = new ArrayList<>();
		}
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interests == null) ? 0 : interests.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (interests == null) {
			if (other.interests != null)
				return false;
		} else if (!interests.equals(other.interests))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", interests=");
		builder.append(interests);
		builder.append("]");
		return builder.toString();
	}
}
