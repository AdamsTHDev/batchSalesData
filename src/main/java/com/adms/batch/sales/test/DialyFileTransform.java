package com.adms.batch.sales.test;

import java.io.File;

public interface DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile) throws Exception;

}
