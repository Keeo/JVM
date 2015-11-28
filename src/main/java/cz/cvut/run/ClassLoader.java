package cz.cvut.run;

import java.io.File;

public class ClassLoader {
	private File file;
	
	
	ClassLoader(File file) throws Exception{
		this.file = file;
		if (this.file.exists() && !this.file.isDirectory()){
			readFile();
		}else{
			throw new Exception("Unsupported file " + this.file.getAbsolutePath() + "!");
		}
	}
	
	private void readFile(){
		
	}
	
	

}
