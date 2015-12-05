package cz.cvut.run.attributes;

import cz.cvut.run.classfile.Attribute;

public class CodeAttribute extends Attribute {

	public CodeAttribute(byte[] attributeNameIndex, byte[] attributeLength) {
		super(attributeNameIndex, attributeLength);
	}
	
	@Override
	public void setAttributeInfo(byte[] attributeInfo) {
		
	}

}
