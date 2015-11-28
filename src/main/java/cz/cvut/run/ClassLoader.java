package cz.cvut.run;

import java.io.File;
import java.io.FileInputStream;

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
	
	private byte[] access_flags = new byte[2];
	private byte[] this_class = new byte[2];
	private byte[] super_class = new byte[2];
	private byte[] interface_count = new byte[2];
	
	private byte[] attributes_count = new byte[2];
	private byte[] fields_count = new byte[2];
	private byte[] methods_count = new byte[2];
	
	
	
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
		
		fis.read(access_flags, 0, 2);
		fis.read(this_class, 0, 2);
		fis.read(super_class, 0, 2);
		fis.read(interface_count, 0, 2);
		readInterfaces();
		
		fis.read(fields_count, 0, 2);
		readFields();
		
		fis.read(methods_count, 0, 2);
		readMethods();
		
		fis.read(attributes_count, 0, 2);
		readAttributes();
		
	}

	private void readAttributes() {
		// TODO Auto-generated method stub
		
	}

	private void readMethods() {
		// TODO Auto-generated method stub
		
	}

	private void readFields() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metoda naète všechny interfejsi
	 * @throws Exception
	 */
	private void readInterfaces() throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metoda naète všechny prvky z Constant poolu
	 * @throws Exception
	 */
	private void readConstants() throws Exception {
		byte[] tagArr = new byte[1];
		Object[] constantPool = new Object[constantPoolCount];
		
		for (int i=0; i<constantPoolCount-1; i++){
			fis.read(tagArr, 0, 1);
			byte tag = tagArr[0];
			switch(tag){
				case TAG_INTEGER:{					// 4 - uvádí poèet bytu za tagem
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_FLOAT:{
					// 4
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_LONG:{
					// 4 high  + 4 low
					byte[] value = new byte[8]; 
					fis.read(value, 0, 8);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_DOUBLE:{
					// 4 high + 4 low
					byte[] value = new byte[8]; 
					fis.read(value, 0, 8);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_UTF8:{
					// 2 length + length
					byte[] length = new byte[2];
					fis.read(length, 0, 2);
					byte[] value = new byte[length[1] + 255* length[0]];
					fis.read(value, 0, value.length);
					byte[] finalValue = new byte[2 + value.length];
					finalValue[0] = length[0];
					for(int j=2; j<finalValue.length; j++){
						finalValue[j] = value[j-2];
					}
					constantPool[i] = new constPoolObject(tag, finalValue);
					break;
				}
				case TAG_STRING:{
					// 2
					byte[] value = new byte[2]; 
					fis.read(value, 0, 2);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_CLASS:{
					// 2
					byte[] value = new byte[2]; 
					fis.read(value, 0, 2);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_FIELDREF:{
					// 2 class index + 2 name and type index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_METHODREF:{
					// 2 class index 2 name and type index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_INTERFACE_METHODREF:{
					// 2 class index + 2 name and type index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				case TAG_NAME_AND_TYPE:{
					// 2 name index 2 descriptor index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					break;
				}
				default:
					log.error("Unsupported tag in constant pool!");
					throw new Exception("Unsupported tag in constant pool!");
			}
			
		}
		for (int i=0; i<constantPoolCount-1; i++){
			log.info(((constPoolObject) constantPool[i]).getTag());
		}
	}
	
	/**
	 * Tøída která reprezentuje jednu hodnotu z constatnt poolu
	 * @author Jeff
	 *
	 */
	private class constPoolObject{
		private byte tag;
		private byte[] value;
		constPoolObject(byte tag, byte[] value){
			this.tag = tag;
			this.value = value;
		}
		
		public byte getTag(){
			return this.tag;
		}
		public byte[] getValue(){
			return this.value;
		}
	}
	

}
