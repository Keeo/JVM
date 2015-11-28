package cz.cvut.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import cz.cvut.run.constants.Constants;

public class ClassLoader {
	private static final Logger log = Logger.getLogger(ClassLoader.class);
	private static final byte TAG_INTEGER = 3;
	private static final byte TAG_FLOAT = 4;
	private static final byte TAG_LONG = 5;
	private static final byte TAG_DOUBLE = 6;
	private static final byte TAG_UTF8 = 1;
	private static final byte TAG_STRING = 8;
	private static final byte TAG_CLASS = 7;
	private static final byte TAG_FIELDREF = 9;
	private static final byte TAG_METHODREF = 10;
	private static final byte TAG_INTERFACE_METHODREF = 11;
	private static final byte TAG_NAME_AND_TYPE = 12;
	
	
	private File file;
	private FileInputStream fis; 
	private byte[] magic = new byte[4];
	private byte[] minor_version = new byte[2];
	private byte[] major_version = new byte[2];
	private byte[] constant_pool_count = new byte[2];
	private int constantPoolCount =0;
	
	
	ClassLoader(File file) throws Exception{
		this.file = file;
		if (this.file.exists() && !this.file.isDirectory()){
			readFile();
			fis.close();
		}else{
			log.error("Unsupported file " + this.file.getAbsolutePath() + "!");
			throw new Exception("Unsupported file " + this.file.getAbsolutePath() + "!");
		}
	}
	
	private void readFile() throws Exception{
		fis = new FileInputStream(file);
		fis.read(magic, 0, 4);
		if (!String.format("0x%02X%02X%02X%02X", magic[0], magic[1], magic[2], magic[3]).equals(Constants.JAVA_MAGIC)){
			log.error("Bad file format: " + file.getAbsolutePath());
			throw new Exception("Bad file format!");
		}
		fis.read(minor_version, 0, 2);

		fis.read(major_version, 0, 2);
		fis.read(constant_pool_count, 0, 2);
		constantPoolCount = constant_pool_count[1] + 255*constant_pool_count[0];
		readConstants();
		
		
		
	}

	private void readConstants() throws IOException {
		byte[] tagArr = new byte[1];
		for (int i=0; i<constantPoolCount; i++){
			fis.read(tagArr, 0, 1);
			byte tag = tagArr[0];
			switch(tag){
				case TAG_INTEGER:{
					// 4 - uvádí poèet bytu za tagem
					break;
				}
				case TAG_FLOAT:{
					// 4
					break;
				}
				case TAG_LONG:{
					// 4 high  + 4 low
					break;
				}
				case TAG_DOUBLE:{
					// 4 high + 4 low
					break;
				}
				case TAG_UTF8:{
					// 2 length + length 
					break;
				}
				case TAG_STRING:{
					// 2
					break;
				}
				case TAG_CLASS:{
					// 2
					break;
				}
				case TAG_FIELDREF:{
					// 2 class index + 2 name and type index
					break;
				}
				case TAG_METHODREF:{
					// 2 class index 2 name and type index
					break;
				}
				case TAG_INTERFACE_METHODREF:{
					// 2 class index + 2 name and type index
					break;
				}
				case TAG_NAME_AND_TYPE:{
					// 2 name index 2 descriptor index
					break;
				}			
			}
			
		}
	}
	
	

}
