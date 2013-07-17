package com.cyra.dao;

import java.util.List;

import com.cyra.model.UserProfile;

public interface CsvDao {

	public void save(List<UserProfile> listOfUserProfiles);

	public List<UserProfile> listUserProfiles();
}
