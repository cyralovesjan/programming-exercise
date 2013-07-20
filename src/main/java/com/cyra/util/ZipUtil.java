package com.cyra.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public final class ZipUtil {

	private ZipUtil() {
		super();
	}

	public static byte[] toZip(List<TextFile> textFiles) throws ArchiveException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ArchiveOutputStream aos = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, baos);
		try {
			for (int i = 0; i < textFiles.size(); i++) {
				ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(textFiles.get(i).getFileName());
				aos.putArchiveEntry(zipArchiveEntry);
				String textFileContent = textFiles.get(i).getContents();
				aos.write(textFileContent.getBytes());
				aos.closeArchiveEntry();
			}
			aos.finish();
		} finally {
			aos.close();
			baos.close();
		}
		return baos.toByteArray();
	}

}
