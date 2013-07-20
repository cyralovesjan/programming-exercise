package com.cyra.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

public class ZipUtilLearningTest {

	@Test
	public void testZip() throws ArchiveException, IOException {
		String s1 = "Alpha\nBeta";
		String s2 = "Charlie\nDelta";
		String s3 = "Echo\nFoxtrot";

		TextFile[] textFileArray = new TextFile[] { new TextFile("first-file.txt", s1),
				new TextFile("second-file.txt", s2), new TextFile("third-file.txt", s3) };
		List<TextFile> textFiles = Arrays.asList(textFileArray);
		byte[] zipBytes = ZipUtil.toZip(textFiles);
		FileOutputStream fos = new FileOutputStream(new File("/home/jan/result-")
				+ DateFormatUtils.format(new Date(), "yyyy-MM-dd-HHmmss") + ".zip");
		fos.write(zipBytes);
		fos.close();
	}

}
