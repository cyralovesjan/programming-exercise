package com.cyra.util;

import java.util.ArrayList;
import java.util.List;

import com.cyra.model.UserProfile;

public class InMemDb {
	private static InMemDb singleInstance;

	private List<UserProfile> userProfileList = new ArrayList<UserProfile>();

	public static InMemDb getInstance() {
		if (singleInstance == null) {
			singleInstance = new InMemDb();
		}
		return singleInstance;
	}

	public void save(List<UserProfile> userProfiles) {
		userProfileList.addAll(userProfiles);
	}

	public List<UserProfile> getUserProfileList() {
		return userProfileList;
	}

	public void deleteAll() {
		userProfileList.clear();
	}

}
