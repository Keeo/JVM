package cz.cvut.run.classfile.constantpool;

import cz.cvut.run.classfile.ConstantPoolElement;
import cz.cvut.run.constants.Constants;

public class ConstInterfaceMethodRefInfo extends ConstantPoolElement {
	public ConstInterfaceMethodRefInfo(){
		this.tag = Constants.TAG_METHODREF;
	}
}
