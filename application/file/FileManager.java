package application.file;

import java.io.File;
import java.io.IOException;


public class FileManager {	
	
	public File file(String filename) {

//		Path user = Paths.get(System.getProperty("user.home"));
//		String userPath = user + "";
//		File file = new File(userPath + "\\" + filename);
		
		File file = new File("src/application/" + filename);
		
		return file;
	}
	
	
	
	
	public void erstelleDatei(String filename) {
		
		if (!file(filename).exists()) {

			try {
				file(filename).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
