package cz.cvut.run.classfile;

import java.util.ArrayList;

import cz.cvut.run.utils.Utils;

public class Method extends ClassElement {
	private byte[] access_flags;
	private int name_index;
	private int descriptor_index;
	private int attributes_count;
	private ArrayList<Attribute> attributes_info;
	
	public Method(byte[] access_flags, byte[] name_index, byte[] descriptor_index, byte[] attributes_count){
		this.access_flags = access_flags;
		this.name_index = Utils.parseByteToInt(name_index);
		this.descriptor_index = Utils.parseByteToInt(descriptor_index);
		this.attributes_count = Utils.parseByteToInt(attributes_count);
	}

	
	public byte[] getAccess_flags() {
		return access_flags;
	}

	public void setAccess_flags(byte[] access_flags) {
		this.access_flags = access_flags;
	}

	public int getName_index() {
		return name_index;
	}

	public void setName_index(int name_index) {
		this.name_index = name_index;
	}

	public int getDescriptor_index() {
		return descriptor_index;
	}

	public void setDescriptor_index(int descriptor_index) {
		this.descriptor_index = descriptor_index;
	}

	public int getAttributesCount() {
		return attributes_count;
	}

	public void setAttributesCount(int attributes_count) {
		this.attributes_count = attributes_count;
	}

	public ArrayList<Attribute> getAttributes_info() {
		return attributes_info;
	}

	public void setAttributes_info(ArrayList<Attribute> attributes_info) {
		this.attributes_info = attributes_info;
	}

}