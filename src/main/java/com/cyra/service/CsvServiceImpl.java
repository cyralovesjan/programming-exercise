package com.cyra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyra.dao.CsvDao;
import com.cyra.model.UserProfile;

@Service
public class CsvServiceImpl implements CsvService {

	@Autowired
	private CsvDao csvDao;

	@Override
	public void save(List<UserProfile> listOfUserProfiles) {
		csvDao.save(listOfUserProfiles);
		
	}

	@Override
	public List<UserProfile> listUserProfiles() {
		return csvDao.listUserProfiles();
		
	}

}
