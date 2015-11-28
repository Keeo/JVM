package cz.cvut.run;

import java.io.File;
import java.io.FileInputStream;

import cz.cvut.run.constants.Constants;

public class ClassLoader {
	private File file;
	private FileInputStream fis; 
	private byte[] magic = new byte[4];
	
	
	ClassLoader(File file) throws Exception{
		this.file = file;
		if (this.file.exists() && !this.file.isDirectory()){
			readFile();
		}else{
			throw new Exception("Unsupported file " + this.file.getAbsolutePath() + "!");
		}
	}
	
	private void readFile() throws Exception{
		fis = new FileInputStream(file);
		fis.read(magic, 0, 4);
		if (!String.format("0x%02X%02X%02X%02X", magic[0], magic[1], magic[2], magic[3]).equals(Constants.JAVA_MAGIC)){
			throw new Exception("Bad file format!");
		}
	}
	
	

}
