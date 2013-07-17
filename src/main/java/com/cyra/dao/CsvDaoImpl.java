package com.cyra.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyra.model.UserProfile;
import com.cyra.util.InMemDb;

@Repository
public class CsvDaoImpl implements CsvDao {

	private InMemDb dbHelper = InMemDb.getInstance();
	
	@Override
	public void save(List<UserProfile> listOfUserProfiles) {
		dbHelper.save(listOfUserProfiles);
	}

	@Override
	public List<UserProfile> listUserProfiles() {
		return dbHelper.getUserProfileList();
		
	}
}
