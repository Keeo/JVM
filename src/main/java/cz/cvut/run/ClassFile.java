package cz.cvut.run;

import java.util.ArrayList;

import cz.cvut.run.classfile.Attribute;
import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.classfile.Field;
import cz.cvut.run.classfile.Interface;
import cz.cvut.run.classfile.Method;

public class ClassFile {
	private int minorVersion;
	private int majorVersion;
	private int constantPoolCount;
	private ArrayList<ConstantPoolElement> constantPool;
	private byte[] accessFlags;
	private byte[] thisClass;
	private int superClass;
	private int interfaceCount;
	private ArrayList<Interface> interfaces;
	private int fieldCount;
	private ArrayList<Field> fields;
	private int methodsCount;
	private ArrayList<Method> methods;
	private int attributesCount;
	private ArrayList<Attribute> attributes;
	
	

	public int getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	public int getMajorVersion() {
		return majorVersion;
	}
	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	public int getConstantPoolCount() {
		return constantPoolCount;
	}
	public void setConstantPoolCount(int constantPoolCount) {
		this.constantPoolCount = constantPoolCount;
	}
	public byte[] getAccessFlags() {
		return accessFlags;
	}
	public void setAccessFlags(byte[] access_flags) {
		this.accessFlags = access_flags;
	}
	public byte[] getThisClass() {
		return thisClass;
	}
	public void setThisClass(byte[] this_class) {
		this.thisClass = this_class;
	}
	public int getSuperClass() {
		return superClass;
	}
	public void setSuperClass(int superClass) {
		this.superClass = superClass;
	}
	public int getInterfaceCount() {
		return interfaceCount;
	}
	public void setInterfaceCount(int interfaceCount) {
		this.interfaceCount = interfaceCount;
	}
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
	public int getMethodsCount() {
		return methodsCount;
	}
	public void setMethodsCount(int methodsCount) {
		this.methodsCount = methodsCount;
	}
	public int getAttributesCount() {
		return attributesCount;
	}
	public void setAttributesCount(int attributesCount) {
		this.attributesCount = attributesCount;
	}
	public ArrayList<ConstantPoolElement> getConstantPool() {
		return constantPool;
	}
	public void setConstantPool(ArrayList<ConstantPoolElement> constantPool) {
		this.constantPool = constantPool;
	}
	public ArrayList<Interface> getInterfaces() {
		return interfaces;
	}
	public ArrayList<Field> getFields() {
		return fields;
	}
	public ArrayList<Method> getMethods() {
		return methods;
	}
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	public void settInterfaces(ArrayList<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}
	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	
	
}
