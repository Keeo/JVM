package cz.cvut.run;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;

import cz.cvut.run.constants.Constants;
import cz.cvut.run.utils.Utils;

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
	private int interfaceCount = 0;
	
	private byte[] attributes_count = new byte[2];
	private int attributesCount = 0;
	
	private byte[] fields_count = new byte[2];
	private int fieldsCount = 0;
	
	private byte[] methods_count = new byte[2];
	private int methodsCount = 0;
	
	Object[] constantPool;
	Object[] fields;
	Object[] methods;
	Object[] attributes;
	
	
	
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
		
		//
		// Read Versions
		//
		fis.read(minor_version, 0, 2);
		log.debug("Minor version: \t\t" + Utils.getHexa(minor_version));
		fis.read(major_version, 0, 2);
		log.debug("Major version: \t\t" + Utils.getHexa(major_version));
		
		
		//
		// Read constant pool
		//
		fis.read(constant_pool_count, 0, 2);
		log.debug("Constant pool count: \t" + Utils.getHexa(constant_pool_count));
		constantPoolCount = Utils.parseByteToInt(constant_pool_count);
		readConstants();
		
		
		// 
		//Read access flags and class
		//
		fis.read(access_flags, 0, 2);
		log.debug("Access flags: \t\t" + Utils.getHexa(access_flags));
		
		fis.read(this_class, 0, 2);
		log.debug("This class: \t\t" + Utils.getHexa(this_class));
		
		fis.read(super_class, 0, 2);
		log.debug("Super class: \t\t" + Utils.getHexa(super_class));
		
		
		//
		// Read interfaces
		//
		fis.read(interface_count, 0, 2);
		log.debug("Interface count: \t\t" + Utils.getHexa(interface_count));
		interfaceCount = Utils.parseByteToInt(interface_count);
		readInterfaces();
		
		
		//
		// Read fields
		//
		fis.read(fields_count, 0, 2);
		log.debug("Fields count: \t\t" + Utils.getHexa(fields_count));
		fieldsCount = Utils.parseByteToInt(fields_count);
		readFields();

		//
		// Read methods
		//
		fis.read(methods_count, 0, 2);
		log.debug("Methods count: \t\t" + Utils.getHexa(methods_count));
		methodsCount = Utils.parseByteToInt(methods_count);
		readMethods();
		
		//
		// Read attributes
		//
		fis.read(attributes_count, 0, 2);
		log.debug("Attributes count: \t\t" + Utils.getHexa(attributes_count));
		attributesCount = Utils.parseByteToInt(attributes_count);
		readAttributes();		
	}

	private void readAttributes() throws Exception {
		attributes = new Object[attributesCount];
		for (int i=0; i<attributesCount; i++){
			attributes[i] = new attributeObject();
		}
	}

	private void readMethods() throws Exception {
		methods = new Object[methodsCount];
		for (int i=0; i<methodsCount; i++){
			methods[i] = new methodsObject();
		}
		
	}

	private void readFields() throws Exception {
		fields = new Object[fieldsCount];
		for (int i=0; i<fieldsCount; i++){
			fields[i] = new fieldObject();
		}
	}

	/**
	 * Metoda naète všechny interfejsi - nejsou žádné, proto teï nic nenaèítá
	 * @throws Exception
	 */
	private void readInterfaces() throws Exception{
		byte[] interfaceRef = new byte[2];
		for(int i=0; i<interfaceCount; i++){
			fis.read(interfaceRef, 0, 2);
			log.debug("Interface reference: \t\t" + Utils.getHexa(interfaceRef));
		}
		
	}
	
	/**
	 * Metoda naète všechny prvky z Constant poolu
	 * @throws Exception
	 */
	private void readConstants() throws Exception {
		byte[] tagArr = new byte[1];
		constantPool = new Object[constantPoolCount];
		
		for (int i=0; i<constantPoolCount-1; i++){
			fis.read(tagArr, 0, 1);
			
			byte tag = tagArr[0];
			switch(tag){
				case TAG_INTEGER:{					// 4 - uvádí poèet bytu za tagem
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_FLOAT:{
					// 4
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_LONG:{
					// 4 high  + 4 low
					byte[] value = new byte[8]; 
					fis.read(value, 0, 8);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_DOUBLE:{
					// 4 high + 4 low
					byte[] value = new byte[8]; 
					fis.read(value, 0, 8);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_UTF8:{
					// 2 length + length
					byte[] length = new byte[2];
					fis.read(length, 0, 2);
					byte[] value = new byte[Utils.parseByteToInt(length)];
					fis.read(value, 0, value.length);
					byte[] finalValue = new byte[2 + value.length];
					finalValue[0] = length[0];
					for(int j=2; j<finalValue.length; j++){
						finalValue[j] = value[j-2];
					}
					constantPool[i] = new constPoolObject(tag, finalValue);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(finalValue));
					break;
				}
				case TAG_STRING:{
					// 2
					byte[] value = new byte[2]; 
					fis.read(value, 0, 2);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_CLASS:{
					// 2
					byte[] value = new byte[2]; 
					fis.read(value, 0, 2);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_FIELDREF:{
					// 2 class index + 2 name and type index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_METHODREF:{
					// 2 class index 2 name and type index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_INTERFACE_METHODREF:{
					// 2 class index + 2 name and type index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				case TAG_NAME_AND_TYPE:{
					// 2 name index 2 descriptor index
					byte[] value = new byte[4]; 
					fis.read(value, 0, 4);
					constantPool[i] = new constPoolObject(tag, value);
					log.debug("Constant pool value: \t" + Utils.getHexa(tagArr) + "\t" + Utils.getHexa(value));
					break;
				}
				default:
					log.error("Unsupported tag in constant pool!");
					throw new Exception("Unsupported tag in constant pool!");
			}
			
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
	
	private class fieldObject{
		private byte[] access_flags = new byte[2];
		private byte[] name_index = new byte[2];
		private byte[] descriptor_index = new byte[2];
		private byte[] attributes_count = new byte[2];
		private Object[] attributes_info;
		fieldObject() throws Exception{
			fis.read(access_flags, 0, 2);
			log.debug("FIELD: Access flags: \t" + Utils.getHexa(access_flags));
			
			fis.read(name_index, 0, 2);
			log.debug("FIELD: Name index: \t\t" + Utils.getHexa(name_index));
			
			fis.read(descriptor_index, 0, 2);
			log.debug("FIELD: Descriptor index: \t" + Utils.getHexa(descriptor_index));
			
			fis.read(attributes_count, 0, 2);
			log.debug("FIELD: Attribures count: \t" + Utils.getHexa(attributes_count));
			
			attributes_info = new Object[Utils.parseByteToInt(attributes_count)];
			for(int i=0; i<Utils.parseByteToInt(attributes_count); i++){
				attributes_info[i] = new attributeObject();
			}
		}
	}
	
	private class attributeObject{
		private byte[] attribute_name_index = new byte[2];
		private byte[] attribute_length = new byte[4];
		private Object[] attribute_info;
		
		attributeObject() throws Exception{
			fis.read(attribute_name_index, 0, 2);
			log.debug("ATTR: Attribute name index: " + Utils.getHexa(attribute_name_index));
			
			fis.read(attribute_length, 0, 4);
			log.debug("ATTR: Attribute length: \t" + Utils.getHexa(attribute_length));
			
			attribute_info = new Object[Utils.parseByteToInt(attribute_length)];
			for (int i=0; i<Utils.parseByteToInt(attribute_length); i++){
				byte[] temp = new byte[1];
				fis.read(temp, 0, 1);
				log.debug("ATTR: Attribute info: \t" + Utils.getHexa(temp));
				attribute_info[i] = temp;
			}
		}
	}
	
	private class methodsObject{
		private byte[] access_flags = new byte[2];
		private byte[] name_index = new byte[2];
		private byte[] descriptor_index = new byte[2];
		private byte[] attributes_count = new byte[2];
		private Object[] attributes_info;
		
		methodsObject() throws Exception{
			fis.read(access_flags, 0, 2);
			log.debug("METH: Access flags: \t" + Utils.getHexa(access_flags));
			
			fis.read(name_index, 0, 2);
			log.debug("METH: Name index: \t\t" + Utils.getHexa(name_index));
			
			fis.read(descriptor_index, 0, 2);
			log.debug("METH: Descriptor index: \t" + Utils.getHexa(descriptor_index));
			
			fis.read(attributes_count, 0, 2);
			log.debug("METH: Attribures count: \t" + Utils.getHexa(attributes_count));
			
			attributes_info = new Object[Utils.parseByteToInt(attributes_count)];
			for(int i=0; i<Utils.parseByteToInt(attributes_count); i++){
				attributes_info[i] = new attributeObject();
			}
			
		}
	}

}
