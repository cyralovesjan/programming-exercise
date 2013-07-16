package com.cyra.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cyra.model.UserProfile;

public final class CsvParserUtil {

	private CsvParserUtil() {
		
	}
	
	public static List<UserProfile> parseFile(File csvFile) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<UserProfile> userProfiles = new ArrayList<UserProfile>();
		try {
			 
			br = new BufferedReader(new FileReader (csvFile));
			while ((line = br.readLine()) != null) {
	 
				String[] user = line.split(cvsSplitBy);
				
				UserProfile userProfile = new UserProfile(user[0], user[1], user[2]);
				userProfiles.add(userProfile);
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		return userProfiles;
	  }
}
