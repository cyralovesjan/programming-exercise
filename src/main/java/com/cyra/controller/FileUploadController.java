package com.cyra.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cyra.form.MultiFileUploadForm;
import com.cyra.model.UserProfile;
import com.cyra.service.CsvService;
import com.cyra.util.CsvParserUtil;

@Controller
public class FileUploadController {

	@Autowired
	private CsvService csvService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") MultiFileUploadForm uploadForm, Model map) throws IllegalStateException, IOException {

		List<MultipartFile> files = uploadForm.getFiles();

		List<String> fileNames = new ArrayList<String>();

		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				fileNames.add(fileName);
				List<UserProfile> userProfile = CsvParserUtil.parseFile(multipartFile.getInputStream());
				csvService.save(userProfile);
			}
		}

		for (UserProfile user : csvService.listUserProfiles()) {
			System.out.println(String.format("Company %s Name %s Position %s", user.getCompany(), user.getName(), user.getPosition()));
		}

		map.addAttribute("files", fileNames);
		return "file_upload_success";
	}

	@RequestMapping(value = "/processrulefile", method = RequestMethod.POST)
	public String processrulefile(@ModelAttribute("uploadForm") MultiFileUploadForm uploadForm, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

		List<MultipartFile> files = uploadForm.getFiles();

		System.out.println("LIST: " + csvService.listUserProfiles());
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
				kbuilder.add(ResourceFactory.newInputStreamResource(multipartFile.getInputStream()), ResourceType.DRL);

				KnowledgeBase kbase = kbuilder.newKnowledgeBase();
				StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

				Map<String, List<UserProfile>> map = new HashMap<String, List<UserProfile>>();
				List<UserProfile> userLists = Collections.emptyList();
				for (UserProfile user : csvService.listUserProfiles()) {
					StringBuilder key = new StringBuilder();
					ksession.setGlobal("key", key);
					ksession.execute(user);
					if (map.containsKey(key.toString())) {
						userLists = map.get(key.toString());
					} else {
						userLists = new ArrayList<UserProfile>();
					}

					userLists.add(user);
					map.put(key.toString(), userLists);
				}

				for (Entry<String, List<UserProfile>> entry : map.entrySet()) {
					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
					String filename = entry.getKey() + ".txt";
					System.out.println("FILENAME: " + filename);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					// write it
					StringBuilder sb = new StringBuilder();
					sb.append("Company\t");
					sb.append("Name\t");
					sb.append("Position\t");
					sb.append("\n");
					
					for (UserProfile userToWrite : entry.getValue()) {
						sb.append(userToWrite.getCompany());
						sb.append("\t");
						sb.append(userToWrite.getName());
						sb.append("\t");
						sb.append(userToWrite.getPosition());
						sb.append("\n");

						
					}
					
					System.out.println("TEST::::" + sb.toString());
					
					os.write(sb.toString().getBytes());
					byte[] byteAray = os.toByteArray();
					
					ServletOutputStream outStream = response.getOutputStream();
					response.setContentType("text/plain");
					response.setContentLength(byteAray.length);
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					outStream.write(byteAray);
					outStream.close();
					
				}
			}

		}
		return "file_upload_success";
	}

}
