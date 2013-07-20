package com.cyra.util;

public class TextFile {

	private String fileName;
	private String contents;

	public TextFile(String fileName, String contents) {
		super();
		this.fileName = fileName;
		this.contents = contents;
	}

	public String getFileName() {
		return fileName;
	}

	public String getContents() {
		return contents;
	}

}
