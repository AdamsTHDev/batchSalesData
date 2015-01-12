package com.adms.batch.sales.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileWalker {
	private List<String> fileList;

	public List<String> getFileList()
	{
		return fileList;
	}

	public void walk(String path, FilenameFilter filter)
	{
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list)
		{
			if (f.isDirectory())
			{
				walk(f.getAbsolutePath(), filter);
//				System.out.println("Dir:" + f.getAbsoluteFile());
			}
			else
			{
//				System.out.println("File:" + f.getParentFile().getAbsolutePath());
				
				if (filter.accept(f.getParentFile(), f.getName()))
				{
					if (this.fileList == null)
					{
						this.fileList = new ArrayList<String>();
					}
					
					this.fileList.add(f.getAbsolutePath());
				}
			}
		}
	}

	public static void main(String[] args)
	{
		FileWalker fw = new FileWalker();
		fw.walk("D:/Work/Report/DailyReport/201409/report of 201409", new FilenameFilter()
		{
			
			public boolean accept(File dir, String name)
			{
				return name.contains("QC_Reconfirm.xls");
			}
		});
		
		for (String filename : fw.getFileList())
		{
			System.out.println(filename);
		}
	}

}
