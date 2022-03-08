package task1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Addition {
	private List<String> listPathFile;
	private String folderPath;
	
	public Addition(String path) {
		listPathFile = new ArrayList<>();
		folderPath = path;
	}
	
	private void calcPathFile(){
		File folder = new File(folderPath);
		File[] listFile = folder.listFiles();
		
		for(File targetFile : listFile) {
			String tmpPath = targetFile.getAbsolutePath();
			listPathFile.add(tmpPath);
		}
	}
	
	public List<String> getListPathFile() {
		calcPathFile();
		return listPathFile;
	}
}