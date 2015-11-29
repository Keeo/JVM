package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstNameAndTypeInfo extends ConstantPoolElement {
	public ConstNameAndTypeInfo(){
		this.tag = Constants.TAG_NAME_AND_TYPE;
	}
}
