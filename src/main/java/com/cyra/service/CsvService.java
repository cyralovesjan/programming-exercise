package com.cyra.service;

import java.util.List;

import com.cyra.model.UserProfile;

public interface CsvService {

	public void save(List<UserProfile> listOfUserProfiles);
	
	public List<UserProfile> listUserProfiles();
}
