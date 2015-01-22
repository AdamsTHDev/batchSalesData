package com.adms.batch.sales.data.ssis;

import java.io.File;

public interface DialyFileTransform {

	public void transform(String inputFileFormat, File inputFile, String outputFileFormat, File outputFile) throws Exception;

}
