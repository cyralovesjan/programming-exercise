package com.cyra.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cyra.form.FileUploadForm;
import com.cyra.model.UserProfile;
import com.cyra.parser.CsvParserUtil;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm,
			Model map) throws IllegalStateException, IOException {

		List<MultipartFile> files = uploadForm.getFiles();

		List<String> fileNames = new ArrayList<String>();

		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				fileNames.add(fileName);
				File csvFile = multipartToFile(multipartFile);
				List<UserProfile> userProfile = CsvParserUtil.parseFile(csvFile);
				System.out.println(userProfile.size());
				
				for(UserProfile user : userProfile) {
					System.out.println(String.format("Company %s Name %s Position %s" , user.getCompany(), user.getName(), user.getPosition()));
				}

			}
		}

		
		map.addAttribute("files", fileNames);
		return "file_upload_success";
	}
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + 
                                multipart.getOriginalFilename());
        multipart.transferTo(tmpFile);
        return tmpFile;
    }
}
