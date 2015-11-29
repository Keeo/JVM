package cz.cvut.run;

import java.util.ArrayList;

import cz.cvut.run.classfile.Attributes;
import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.classfile.Fields;
import cz.cvut.run.classfile.Interfaces;
import cz.cvut.run.classfile.Methods;

public class ClassFile {
	private int minorVersion;
	private int majorVersion;
	private int constantPoolCount;
	private ArrayList<ConstantPoolElement> constantPool;
	private int accessFlags;
	private int thisClass;
	private int superClass;
	private int interfaceCount;
	private Interfaces interfaces = new Interfaces();
	private int fieldCount;
	private Fields fields = new Fields();
	private int methodsCount;
	private Methods methods = new Methods();
	private int attributesCount;
	private Attributes attributes = new Attributes();
	
	

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
	public int getAccessFlags() {
		return accessFlags;
	}
	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}
	public int getThisClass() {
		return thisClass;
	}
	public void setThisClass(int thisClass) {
		this.thisClass = thisClass;
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
	public Interfaces getInterfaces() {
		return interfaces;
	}
	public Fields getFields() {
		return fields;
	}
	public Methods getMethods() {
		return methods;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	
	
	
}
