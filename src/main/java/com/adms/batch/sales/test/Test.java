package com.adms.batch.sales.test;

public class Test {

	public static void main(String[] args)
	{
		String outputFileNameSsis = "D:\\asdf\\201504\\TELE\\AUTO_MTIKBANK\\SC PA CASHBACK\\20150401\\ADFSF_MTD_ABC15.xlsx";
		String outputPathSsis = outputFileNameSsis.substring(0, outputFileNameSsis.lastIndexOf(System.getProperty("file.separator")));

		int beginIndex = outputFileNameSsis.indexOf("_MTD_") + 5;
		int endIndex = beginIndex + 5;
		outputPathSsis = outputPathSsis + System.getProperty("file.separator") + outputFileNameSsis.substring(beginIndex, endIndex);
		
		System.out.println(outputPathSsis);
	}

}
